package com.ali_nasir.SSGC.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FieldActivityDTO {

    private String faId;   
    private String faType;
    private String faStatus;
    private String unit;
    private String region;
    private String zones;
    private String subzone;
    private String areaDescr;
    private LocalDateTime schedDttm;
    private LocalDateTime foWorkedDttm;
    private String name;
    private String fullAddress;   
    private String meterNo;
    private String remarks;
    private String reason;
    private String bookRoute;

    public FieldActivityDTO(String faId, String faType, String faStatus,
                            String unit, String region, String zones, String subzone, String areaDescr,
                            LocalDateTime schedDttm,  LocalDateTime foWorkedDttm,
                            String name, String fullAddress, String meterNo,
                            String remarks, String reason, String bookRoute) {
        this.faId = faId;
        this.faType = faType;
        this.faStatus = faStatus;
        this.unit = unit;
        this.region = region;
        this.zones = zones;
        this.subzone = subzone;
        this.areaDescr = areaDescr;
        this.schedDttm = schedDttm;
        this.foWorkedDttm = foWorkedDttm;
        this.name = name;
        this.fullAddress = fullAddress;  
        this.meterNo = meterNo;
        this.remarks = remarks;
        this.reason = reason;
        this.bookRoute = bookRoute;
    }

    public FieldActivityDTO() {}
}

