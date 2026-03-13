package com.oilmonitor.wellmonitor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
//import java.time.LocalTime;

@Entity
@Table(name = "alarms")
@Data
@NoArgsConstructor
public class Alarm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime dateTime; // Date and Time
    
    private String state; // UNACK, ACK
    
    private String operator; // ONSERV1/Operator, ONENG/Engineer
    
    @Column(nullable = false)
    private Integer priority; // 1 (highest)
    
    private String comment; // TEQ REBOILER TEMP CONTROLLER, TRANSMITTER - FAULTY
    
    private String name; // TIC_330-A3, GDP0004_Q-A1, SERV_OK
    
    private String provider; // WONSERV1InTouch, UnTouch
    
    private String limitType; // LOW, DISCRETE, Dead
    
    private Boolean silenced = false; // Acknowl., Filter
    
    // Връзка с колонката (ако алармата е за конкретна колонка)
    @ManyToOne
    @JoinColumn(name = "well_id")
    private Well well;
}