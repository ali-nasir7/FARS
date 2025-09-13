package com.ali_nasir.SSGC.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "locations") 
public class Location {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    @Column(name = "Unit_Cd")
    private String unitCd;

    @Column(name = "Unit_Descr")
    private String unitDescr;

    @Column(name = "Region_Cd")
    private String regionCd;

    @Column(name = "Region_Descr")
    private String regionDescr;

    @Column(name = "Zone_Cd")
    private String zoneCd;

    @Column(name = "Zone_Descr")
    private String zoneDescr;

    @Column(name = "SubZone_Cd")
    private String subZoneCd;

    @Column(name = "SubZone_Descr")
    private String subZoneDescr;

    @Column(name = "area_cd")
    private String areaCd;

    @Column(name = "area_descr")
    private String areaDescr;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUnitCd() { return unitCd; }
    public void setUnitCd(String unitCd) { this.unitCd = unitCd; }

    public String getUnitDescr() { return unitDescr; }
    public void setUnitDescr(String unitDescr) { this.unitDescr = unitDescr; }

    public String getRegionCd() { return regionCd; }
    public void setRegionCd(String regionCd) { this.regionCd = regionCd; }

    public String getRegionDescr() { return regionDescr; }
    public void setRegionDescr(String regionDescr) { this.regionDescr = regionDescr; }

    public String getZoneCd() { return zoneCd; }
    public void setZoneCd(String zoneCd) { this.zoneCd = zoneCd; }

    public String getZoneDescr() { return zoneDescr; }
    public void setZoneDescr(String zoneDescr) { this.zoneDescr = zoneDescr; }

    public String getSubZoneCd() { return subZoneCd; }
    public void setSubZoneCd(String subZoneCd) { this.subZoneCd = subZoneCd; }

    public String getSubZoneDescr() { return subZoneDescr; }
    public void setSubZoneDescr(String subZoneDescr) { this.subZoneDescr = subZoneDescr; }

    public String getAreaCd() { return areaCd; }
    public void setAreaCd(String areaCd) { this.areaCd = areaCd; }

    public String getAreaDescr() { return areaDescr; }
    public void setAreaDescr(String areaDescr) { this.areaDescr = areaDescr; }
}
