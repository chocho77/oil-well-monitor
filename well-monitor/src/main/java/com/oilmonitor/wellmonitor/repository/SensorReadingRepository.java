package com.oilmonitor.wellmonitor.repository;

import com.oilmonitor.wellmonitor.model.SensorReading;
import com.oilmonitor.wellmonitor.model.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    
    // Намери последните 20 четения за конкретна колонка
    List<SensorReading> findTop20ByWellOrderByTimestampDesc(Well well);
    
    // Намери четения за период от време
    List<SensorReading> findByWellAndTimestampBetweenOrderByTimestampAsc(
        Well well, LocalDateTime start, LocalDateTime end);
    
    // Намери всички аларми
    List<SensorReading> findByIsAlarmTrue();
    
    // Намери последното четене за колонка
    @Query("SELECT s FROM SensorReading s WHERE s.well = :well ORDER BY s.timestamp DESC LIMIT 1")
    SensorReading findLatestByWell(@Param("well") Well well);
}
