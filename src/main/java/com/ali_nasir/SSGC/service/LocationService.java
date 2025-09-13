package com.ali_nasir.SSGC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali_nasir.SSGC.Repository.LocationsRepository;

@Service
public class LocationService {

    @Autowired
    private LocationsRepository repo;

    // Units
    public List<String> getUnits() {
        return repo.findAllUnits();
    }

    // Regions by Unit
    public List<String> getRegions(String unit) {
        unit = (unit != null && !unit.trim().isEmpty()) ? unit.trim().toUpperCase() : null;
        return repo.findRegionsByUnit(unit);
    }

    // Zones by Region (repo method only takes region)
    public List<String> getZones(String region) {
        region = (region != null && !region.trim().isEmpty()) ? region.trim().toUpperCase() : null;
        return repo.findZonesByRegion(region);
    }

    // Subzones by Zone (repo method only takes zone)
    public List<String> getSubzones(String zone) {
        zone = (zone != null && !zone.trim().isEmpty()) ? zone.trim().toUpperCase() : null;
        return repo.findSubzonesByZone(zone);
    }

    // Areas by Subzone (repo method only takes subzone)
    public List<String> getAreas(String subzone) {
        subzone = (subzone != null && !subzone.trim().isEmpty()) ? subzone.trim().toUpperCase() : null;
        return repo.findAreasBySubzone(subzone);
    }
}
