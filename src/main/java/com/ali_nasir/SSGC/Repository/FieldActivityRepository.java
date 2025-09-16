package com.ali_nasir.SSGC.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ali_nasir.SSGC.DTO.FieldActivityDTO;
import com.ali_nasir.SSGC.Entity.FieldActivity;

@Repository
public interface FieldActivityRepository extends JpaRepository<FieldActivity, String> {

    Page<FieldActivity> findAll(Pageable pageable);

    // Trim values (native) so trailing spaces don't cause duplicate items.
    @Query(value = "SELECT DISTINCT TRIM(FA_TYPE) FROM field_activities_data WHERE FA_TYPE IS NOT NULL ORDER BY TRIM(FA_TYPE)", nativeQuery = true)
    List<String> findDistinctFieldActivities();

    @Query(value ="SELECT DISTINCT TRIM(FA_STATUS) FROM field_activities_data WHERE FA_STATUS IS NOT NULL ORDER BY TRIM(FA_STATUS)",nativeQuery = true)
    List<String> findDistinctFaStatus();

    @Query("SELECT new com.ali_nasir.SSGC.DTO.FieldActivityDTO(" +
        "f.faId, f.faType, f.faStatus, f.unit, f.region, f.zones, f.subzone, f.areaDescr, " +
        "f.schedDttm, f.foWorkedDttm, f.name, " +
        "CONCAT(f.address1, ' ', f.city), f.meterNo, f.remarks, f.reason, f.bookRoute) " +
        "FROM FieldActivity f " +
        "LEFT JOIN Location l ON " +
        "LOWER(TRIM(f.unit)) = LOWER(TRIM(l.unitDescr)) AND " +
        "LOWER(TRIM(f.region)) = LOWER(TRIM(l.regionDescr)) AND " +
        "LOWER(TRIM(f.zones)) = LOWER(TRIM(l.zoneDescr)) AND " +
        "LOWER(TRIM(f.subzone)) = LOWER(TRIM(l.subZoneDescr)) AND " +
        "LOWER(TRIM(f.areaDescr)) = LOWER(TRIM(l.areaDescr)) " +
        "WHERE (:unit IS NULL OR LOWER(TRIM(f.unit)) = LOWER(TRIM(:unit))) " +
        "AND (:region IS NULL OR LOWER(TRIM(f.region)) = LOWER(TRIM(:region))) " +
        "AND (:zone IS NULL OR LOWER(TRIM(f.zones)) = LOWER(TRIM(:zone))) " +
        "AND (:subzone IS NULL OR LOWER(TRIM(f.subzone)) = LOWER(TRIM(:subzone))) " +
        "AND (:area IS NULL OR LOWER(TRIM(f.areaDescr)) = LOWER(TRIM(:area))) " +
        "AND (:faType IS NULL OR LOWER(TRIM(f.faType)) = LOWER(TRIM(:faType))) " +
        "AND (:faStatus IS NULL OR LOWER(TRIM(f.faStatus)) = LOWER(TRIM(:faStatus))) " +
            "AND (:startDate IS NULL OR f.schedDttm >= :startDate) " +
        "AND (:endDate IS NULL OR f.schedDttm <= :endDate)")
    Page<FieldActivityDTO> searchActivities(
        @Param("unit") String unit,
        @Param("region") String region,
        @Param("zone") String zone,
        @Param("subzone") String subzone,
        @Param("area") String area,
        @Param("faType") String faType,
        @Param("faStatus") String faStatus,
    @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate,
         Pageable pageable
    );
@Query("SELECT FUNCTION('MONTHNAME', f.schedDttm), COUNT(f) " +
       "FROM FieldActivity f " +
       "WHERE (:faType IS NULL OR f.faType = :faType) " +
       "AND (:unit IS NULL OR f.unit = :unit) " +
       "AND (:region IS NULL OR f.region = :region) " +
       "AND (:zone IS NULL OR f.zones = :zone) " +   
       "AND (:subzone IS NULL OR f.subzone = :subzone) " +
       "AND (:area IS NULL OR f.area = :area) " +
       "GROUP BY FUNCTION('MONTH', f.schedDttm), FUNCTION('MONTHNAME', f.schedDttm) " +
       "ORDER BY FUNCTION('MONTH', f.schedDttm)")
List<Object[]> getActivityStats(
    @Param("faType") String faType,
    @Param("unit") String unit,
    @Param("region") String region,
    @Param("zone") String zone,
    @Param("subzone") String subzone,
    @Param("area") String area
);

}


