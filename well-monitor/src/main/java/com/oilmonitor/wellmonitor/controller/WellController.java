package com.oilmonitor.wellmonitor.controller;

import com.oilmonitor.wellmonitor.model.Well;
import com.oilmonitor.wellmonitor.model.Alarm;
import com.oilmonitor.wellmonitor.repository.WellRepository;
import com.oilmonitor.wellmonitor.repository.AlarmRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class WellController {

    private final WellRepository wellRepository;
    private final AlarmRepository alarmRepository;

    public WellController(WellRepository wellRepository, AlarmRepository alarmRepository) {
        this.wellRepository = wellRepository;
        this.alarmRepository = alarmRepository;
    }

    // ----- WELL ENDPOINTS -----
    
    @GetMapping("/wells")
    public List<Well> getAllWells() {
        return wellRepository.findAll();
    }

    @GetMapping("/wells/{id}")
    public ResponseEntity<Well> getWellById(@PathVariable Long id) {
        Optional<Well> well = wellRepository.findById(id);
        return well.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/wells/name/{name}")
    public ResponseEntity<Well> getWellByName(@PathVariable String name) {
        Optional<Well> well = wellRepository.findAll().stream()
            .filter(w -> w.getName().equalsIgnoreCase(name))
            .findFirst();
        return well.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    // ----- ALARM ENDPOINTS -----
    
    @GetMapping("/alarms")
    public List<Alarm> getAllAlarms() {
        return alarmRepository.findAll();
    }
    
    @GetMapping("/alarms/recent")
    public List<Alarm> getRecentAlarms() {
        return alarmRepository.findTop10ByOrderByDateTimeDesc();
    }
    
    @GetMapping("/alarms/unack")
    public List<Alarm> getUnacknowledgedAlarms() {
        return alarmRepository.findByState("UNACK");
    }
    
    @GetMapping("/alarms/priority/{priority}")
    public List<Alarm> getAlarmsByPriority(@PathVariable Integer priority) {
        return alarmRepository.findByPriorityLessThanEqual(priority);
    }
    
    // ----- DASHBOARD SUMMARY -----
    
    @GetMapping("/dashboard/summary")
    public DashboardSummary getDashboardSummary() {
        List<Alarm> unackAlarms = alarmRepository.findByState("UNACK");
        long highPriorityCount = alarmRepository.findByPriorityLessThanEqual(1).size();
        
        DashboardSummary summary = new DashboardSummary();
        summary.setTotalWells(wellRepository.count());
        summary.setActiveWells(wellRepository.findByStatus("ACTIVE").size());
        summary.setUnacknowledgedAlarms(unackAlarms.size());
        summary.setHighPriorityAlarms((int) highPriorityCount);
        
        return summary;
    }
    
    // Вътрешен клас за обобщена информация
    static class DashboardSummary {
        private long totalWells;
        private long activeWells;
        private int unacknowledgedAlarms;
        private int highPriorityAlarms;
        
        // getters and setters
        public long getTotalWells() { return totalWells; }
        public void setTotalWells(long totalWells) { this.totalWells = totalWells; }
        
        public long getActiveWells() { return activeWells; }
        public void setActiveWells(long activeWells) { this.activeWells = activeWells; }
        
        public int getUnacknowledgedAlarms() { return unacknowledgedAlarms; }
        public void setUnacknowledgedAlarms(int unacknowledgedAlarms) { 
            this.unacknowledgedAlarms = unacknowledgedAlarms; 
        }
        
        public int getHighPriorityAlarms() { return highPriorityAlarms; }
        public void setHighPriorityAlarms(int highPriorityAlarms) { 
            this.highPriorityAlarms = highPriorityAlarms; 
        }
    }
}
