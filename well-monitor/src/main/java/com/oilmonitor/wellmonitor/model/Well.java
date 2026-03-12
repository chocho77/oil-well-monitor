package com.oilmonitor.wellmonitor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "wells")
@Data
@NoArgsConstructor
public class Well {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String location;
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, MAINTENANCE
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
