package com.ali_nasir.SSGC.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.ali_nasir.SSGC.DTO.FieldActivityDTO;
import com.ali_nasir.SSGC.service.FieldActivitiesService;

@RestController
@RequestMapping("/api/activities")    // consistent base path
public class FaDetailController {

    private final FieldActivitiesService service;

  
    public FaDetailController(FieldActivitiesService service) {
        this.service = service;
    }

      @GetMapping("/statuses")
    public List<String> getFieldActivityStatuses() {
        return service.findDistinctFaStatus();
    }
    // Return activity types (use service so logic is centralized)
    @GetMapping("/types")
    public List<String> getActivities(){
        return service.findDistinctFieldActivities();
    }

    // Search (all params optional). We normalize empty strings to null.
    @GetMapping("/search")
public List<FieldActivityDTO> searchActivities(
        @RequestParam(required = false) String unit,
        @RequestParam(required = false) String region,
        @RequestParam(required = false) String zone,
        @RequestParam(required = false) String subzone,
        @RequestParam(required = false) String area,
        @RequestParam(required = false) String faType,
        @RequestParam(required = false) String faStatus,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
) {
    return service.searchActivities(unit, region, zone, subzone, area, faType, faStatus, startDate, endDate);
}

}
