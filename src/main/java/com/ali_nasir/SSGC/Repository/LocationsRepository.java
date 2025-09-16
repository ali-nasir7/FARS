package com.ali_nasir.SSGC.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ali_nasir.SSGC.Entity.Location;

@Repository
public interface LocationsRepository extends JpaRepository<Location, Long> {

    @Query("SELECT DISTINCT l.unitDescr FROM Location l ORDER BY l.unitDescr")
    List<String> findAllUnits();


    @Query("SELECT DISTINCT l.regionDescr FROM Location l WHERE (:unit IS NULL OR l.unitDescr = :unit) ORDER BY l.regionDescr")
    List<String> findRegionsByUnit(@Param("unit") String unit);

     @Query("SELECT DISTINCT l.zoneDescr FROM Location l WHERE (:region IS NULL OR l.regionDescr = :region) ORDER BY l.zoneDescr")
    List<String> findZonesByRegion(@Param("region") String region);

  
    @Query("SELECT DISTINCT f.subzone " +
       "FROM FieldActivity f " +
       "WHERE (:zone IS NULL OR LOWER(TRIM(f.zones)) = LOWER(TRIM(:zone))) " +
       "ORDER BY f.subzone")
List<String> findSubzonesByZone(@Param("zone") String zone);




    
    @Query("SELECT DISTINCT f.areaDescr " +
       "FROM FieldActivity f " +
       "WHERE (:subZone IS NULL OR LOWER(TRIM(f.subzone)) = LOWER(TRIM(:subZone))) " +
       "ORDER BY f.areaDescr")
List<String> findAreasBySubzone(@Param("subZone") String subZone);

}
