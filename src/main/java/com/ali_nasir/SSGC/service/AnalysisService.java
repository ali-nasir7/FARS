package com.ali_nasir.SSGC.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali_nasir.SSGC.Repository.FieldActivityRepository;
@Service
public class AnalysisService {

    @Autowired
    private FieldActivityRepository fieldActivityRepository;

    public Map<String, Object> getActivityStats(String faType, String unit, String region,
                                            String zone, String subzone, String area) {

        List<Object[]> results = fieldActivityRepository.getActivityStats(faType, unit, region, zone, subzone, area);

        List<String> months = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (Object[] row : results) {
            months.add((String) row[0]);        
            counts.add(((Number) row[1]).intValue()); 
        }

        Map<String, Object> response = new HashMap<>();
        response.put("months", months);
        response.put("counts", counts);

        // --- Prediction (based on average of ALL counts) ---
        double prediction = 0.0;
        if (!counts.isEmpty()) {
            prediction = counts.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }
        response.put("prediction", Math.round(prediction));

        // --- Percentage Change (last month vs previous month) ---
        double percentageChange = 0.0;
        if (counts.size() >= 2) {
            int last = counts.get(counts.size() - 1);
            int secondLast = counts.get(counts.size() - 2);

            if (secondLast != 0) {
                percentageChange = ((double) (last - secondLast) / secondLast) * 100.0;
            }
        }
        response.put("percentageChange", Math.round(percentageChange * 10.0) / 10.0); 

        return response;
    }

}

