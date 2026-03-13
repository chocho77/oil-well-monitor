package com.oilmonitor.wellmonitor.controller;

import com.oilmonitor.wellmonitor.model.Well;
import com.oilmonitor.wellmonitor.model.SensorReading;
import com.oilmonitor.wellmonitor.repository.WellRepository;
import com.oilmonitor.wellmonitor.repository.SensorReadingRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wells")
@CrossOrigin(origins = "http://localhost:4200") // за Angular
public class WellController {

    private final WellRepository wellRepository;
    private final SensorReadingRepository sensorReadingRepository;

    public WellController(WellRepository wellRepository, 
                         SensorReadingRepository sensorReadingRepository) {
        this.wellRepository = wellRepository;
        this.sensorReadingRepository = sensorReadingRepository;
    }

    // Всички колонки
    @GetMapping
    public List<Well> getAllWells() {
        return wellRepository.findAll();
    }

    // Една колонка по ID
    @GetMapping("/{id}")
    public ResponseEntity<Well> getWellById(@PathVariable Long id) {
        Optional<Well> well = wellRepository.findById(id);
        return well.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Последните четения за колонка
    @GetMapping("/{id}/latest-readings")
    public ResponseEntity<List<SensorReading>> getLatestReadings(@PathVariable Long id) {
        Optional<Well> well = wellRepository.findById(id);
        if (well.isPresent()) {
            List<SensorReading> readings = 
                sensorReadingRepository.findTop20ByWellOrderByTimestampDesc(well.get());
            return ResponseEntity.ok(readings);
        }
        return ResponseEntity.notFound().build();
    }

    // Всички аларми
    @GetMapping("/alarms")
    public List<SensorReading> getAllAlarms() {
        return sensorReadingRepository.findByIsAlarmTrue();
    }
}