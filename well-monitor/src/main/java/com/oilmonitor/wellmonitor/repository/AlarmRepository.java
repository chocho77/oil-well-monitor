package com.oilmonitor.wellmonitor.repository;

import com.oilmonitor.wellmonitor.model.Alarm;
import com.oilmonitor.wellmonitor.model.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    
    // Всички непотвърдени аларми (UNACK)
    List<Alarm> findByState(String state);
    
    // Всички аларми с висок приоритет
    List<Alarm> findByPriorityLessThanEqual(Integer priority);
    
    // Аларми за конкретна колонка
    List<Alarm> findByWell(Well well);
    
    // Аларми за период
    List<Alarm> findByDateTimeBetweenOrderByDateTimeDesc(LocalDateTime start, LocalDateTime end);
    
    // Последните 10 аларми
    List<Alarm> findTop10ByOrderByDateTimeDesc();
}