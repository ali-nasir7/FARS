package com.ali_nasir.SSGC.Controller;

import com.ali_nasir.SSGC.Repository.LocationsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationsRepository locationRepository;

    public LocationController(LocationsRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/units")
    public List<String> getUnits() { return locationRepository.findAllUnits(); }

    @GetMapping("/regions/{unit}")
    public List<String> getRegions(@PathVariable String unit) { return locationRepository.findRegionsByUnit(unit); }

    @GetMapping("/zones/{region}")
    public List<String> getZones(@PathVariable String region) { return locationRepository.findZonesByRegion(region); }

 @GetMapping("/subzones/{zone}")
public List<String> getSubZones(@PathVariable String zone) { 
    return locationRepository.findSubzonesByZone(zone);
}

 
    @GetMapping("/areas/{subzone}")
    public List<String> getAreas(@PathVariable String subzone) { return locationRepository.findAreasBySubzone(subzone); }
}
