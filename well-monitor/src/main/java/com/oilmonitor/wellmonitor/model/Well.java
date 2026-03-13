package com.oilmonitor.wellmonitor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
//import java.util.List;

@Entity
@Table(name = "wells")
@Data
@NoArgsConstructor
public class Well {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name; // KAVARNA, KALIAKRA
    
    private String location; // OFFSHORE SUBSEA
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, MAINTENANCE
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // ----- СЕНЗОРИ ЗА НАЛЯГАНЕ (PRESSURE TRANSMITTERS) -----
    
    @Column(name = "pt3101")
    private Double pt3101; // Pressure Transmitter 3101 (PSIG)
    
    @Column(name = "pt1105")
    private Double pt1105; // Pressure Transmitter 1105 (Bar(G))
    
    @Column(name = "pt1205")
    private Double pt1205; // Pressure Transmitter 1205 (Bar(G))
    
    @Column(name = "pt1401")
    private Double pt1401; // Pressure Transmitter 1401 (PSIG)
    
    @Column(name = "dp001")
    private Double dp001; // Differential Pressure 001 (Bar)
    
    // ----- КЛАПАНИ (VALVES) -----
    
    @Column(name = "sdv3001")
    private String sdv3001; // Shutdown Valve 3001 (FROM WHCP, FROM CHEM INJ PUMPS)
    
    @Column(name = "sdv4001")
    private String sdv4001; // Shutdown Valve 4001
    
    @Column(name = "sdv1203")
    private Double sdv1203; // Shutdown Valve 1203 (Bar(G))
    
    // ----- ПОЗИЦИИ НА КЛАПАНИ (VALVE POSITIONS) -----
    
    @Column(name = "zt1100")
    private Double zt1100; // Valve position 1100 (%)
    
    @Column(name = "zt1200")
    private Double zt1200; // Valve position 1200 (%)
    
    // ----- ВИБРАЦИИ (VIBRATION) -----
    
    @Column(name = "v460_vibration")
    private Double v460Vibration; // V-460 Vibration
    
    @Column(name = "v410_vibration")
    private Double v410Vibration; // V-410 Vibration
    
    // ----- КОМПРЕСОРИ (COMPRESSORS) -----
    
    @Column(name = "c460")
    private Double c460; // C-460
    
    @Column(name = "c410")
    private Double c410; // C-410
    
    // ----- ДЕБИТ (FLOW) -----
    
    @Column(name = "current_day_totalised_flow")
    private Double currentDayTotalisedFlow; // CURRENT DAY TOTALISED FLOW (RM3)
    
    @Column(name = "previous_day_totalised_flow")
    private Double previousDayTotalisedFlow; // PREVIOUS DAY TOTALISED FLOW (RM3)
    
    // ----- ТЕМПЕРАТУРИ (TEMPERATURES) -----
    
    @Column(name = "tt1100")
    private Double tt1100; // Temperature Transmitter 1100 (DegC)
    
    // ----- BYPASS ИНФОРМАЦИЯ -----
    
    @Column(name = "pt1100lltrp_bypass")
    private Boolean pt1100lltrpBypass = false; // BYPASS PT_1100LLTRP
    
    @Column(name = "pt1200lltrp_bypass")
    private Boolean pt1200lltrpBypass = false; // BYPASS PT_1200LLTRP
    
    @Column(name = "bypass_time_seconds")
    private Integer bypassTimeSeconds = 0; // TIME 50,00.00 SEC
}