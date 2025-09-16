package com.ali_nasir.SSGC.DTO;

public class LocationDTO {
    private Long id;
    private String unitDescr;
    private String regionDescr;
    private String zoneDescr;
    private String subZoneDescr;
    private String areaDescr;

   
    public LocationDTO() {
    }

    
    public LocationDTO(Long id, String unitDescr, String regionDescr, String zoneDescr, String subZoneDescr, String areaDescr) {
        this.id = id;
        this.unitDescr = unitDescr;
        this.regionDescr = regionDescr;
        this.zoneDescr = zoneDescr;
        this.subZoneDescr = subZoneDescr;
        this.areaDescr = areaDescr;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitDescr() {
        return unitDescr;
    }

    public void setUnitDescr(String unitDescr) {
        this.unitDescr = unitDescr;
    }

    public String getRegionDescr() {
        return regionDescr;
    }

    public void setRegionDescr(String regionDescr) {
        this.regionDescr = regionDescr;
    }

    public String getZoneDescr() {
        return zoneDescr;
    }

    public void setZoneDescr(String zoneDescr) {
        this.zoneDescr = zoneDescr;
    }

    public String getSubZoneDescr() {
        return subZoneDescr;
    }

    public void setSubZoneDescr(String subZoneDescr) {
        this.subZoneDescr = subZoneDescr;
    }

    public String getAreaDescr() {
        return areaDescr;
    }

    public void setAreaDescr(String areaDescr) {
        this.areaDescr = areaDescr;
    }
}
