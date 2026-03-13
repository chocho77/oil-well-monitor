package com.oilmonitor.wellmonitor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_readings")
@Data
@NoArgsConstructor
public class SensorReading {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Връзка с колонката (много четения към една колонка)
    @ManyToOne
    @JoinColumn(name = "well_id", nullable = false)
    private Well well;
    
    // Кога е отчетено
    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
    
    // Сензорни данни - засега само дебит, после ще добавим и други
    @Column(name = "flow_rate")
    private Double flowRate; // дебит
    
    @Column(name = "pressure")
    private Double pressure; // налягане
    
    @Column(name = "temperature")
    private Double temperature; // температура
    
    @Column(name = "valve_position")
    private Double valvePosition; // позиция на клапан (%)
    
    // Дали тази стойност е аларма
    private Boolean isAlarm = false;
    
    // Описание на алармата, ако има
    private String alarmMessage;
}