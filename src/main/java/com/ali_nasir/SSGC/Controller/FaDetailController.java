package com.ali_nasir.SSGC.Controller;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ali_nasir.SSGC.DTO.FieldActivityDTO;
import com.ali_nasir.SSGC.service.AnalysisService;
import com.ali_nasir.SSGC.service.FieldActivitiesService;



@RestController
@RequestMapping("/api/activities")    
public class FaDetailController {

    private final FieldActivitiesService service;

  
    public FaDetailController(FieldActivitiesService service) {
        this.service = service;
    }


   @Autowired
   private AnalysisService analysisService;

      @GetMapping("/statuses")
    public List<String> getFieldActivityStatuses() {
        return service.findDistinctFaStatus();
    }
    // Return activity types (use service so logic is centralized)
    @GetMapping("/types")
    public List<String> getActivities(){
        return service.findDistinctFieldActivities();
    }



 @GetMapping("/analysis")
    public Map<String, Object> getAnalysis(
            @RequestParam(required = false) String faType,
            @RequestParam(required = false) String unit,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String zone,
            @RequestParam(required = false) String subzone,
            @RequestParam(required = false) String area
    ) {
        return analysisService.getActivityStats(faType, unit, region, zone, subzone, area);
    }

  

@GetMapping("/search")
public ResponseEntity<Map<String, Object>> searchActivities(
        @RequestParam(required = false) String unit,
        @RequestParam(required = false) String region,
        @RequestParam(required = false) String zone,
        @RequestParam(required = false) String subzone,
        @RequestParam(required = false) String area,
        @RequestParam(required = false) String faType,
        @RequestParam(required = false) String faStatus,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "50") int size
) {
    Page<FieldActivityDTO> resultPage = service.searchActivities(
            unit, region, zone, subzone, area,
            faType, faStatus,
            startDate, endDate,
            page, size
    );

    Map<String, Object> response = new HashMap<>();
    response.put("activities", resultPage.getContent());
    response.put("currentPage", resultPage.getNumber());
    response.put("totalItems", resultPage.getTotalElements());
    response.put("totalPages", resultPage.getTotalPages());

    return ResponseEntity.ok(response);
}



}
