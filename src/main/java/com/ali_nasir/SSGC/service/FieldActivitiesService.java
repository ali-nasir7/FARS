package com.ali_nasir.SSGC.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ali_nasir.SSGC.DTO.FieldActivityDTO;
import com.ali_nasir.SSGC.Repository.FieldActivityRepository;

@Service
public class FieldActivitiesService {

    private final FieldActivityRepository repository;

    public FieldActivitiesService(FieldActivityRepository repository) {
        this.repository = repository;
    }

    // helper: trim & convert empty -> null
    private String normalize(String s) {
        if (s == null) return null;
        s = s.trim();
        return s.isEmpty() ? null : s;
    }

      @Transactional(readOnly = true)
    public List<String> findDistinctFaStatus() {
        return repository.findDistinctFaStatus()
                         .stream()
                         .filter(x -> x != null)
                         .map(String::trim)
                         .distinct()
                         .sorted(String::compareToIgnoreCase)
                         .collect(Collectors.toList());
    }

    
    // return distinct activity types (trimmed & sorted)
    @Transactional(readOnly = true)
    public List<String> findDistinctFieldActivities() {
        return repository.findDistinctFieldActivities()
                         .stream()
                         .filter(x -> x != null)
                         .map(String::trim)
                         .distinct()
                         .sorted(String::compareToIgnoreCase)
                         .collect(Collectors.toList());
    }

 @Transactional(readOnly = true)
public List<FieldActivityDTO> searchActivities(
    String unit, String region, String zone, String subzone, String area,
    String faType, String faStatus,
    LocalDateTime startDate, LocalDateTime endDate
) {
    // normalize all text filters
    unit = normalize(unit);
    region = normalize(region);
    zone = normalize(zone);
    subzone = normalize(subzone);
    area = normalize(area);
    faType = normalize(faType);
    faStatus = normalize(faStatus);

    // call repository query with normalized params
    return repository.searchActivities(
        unit,
        region,
        zone,
        subzone,
        area,
        faType,
        faStatus,
        startDate,
        endDate
    );
}
}