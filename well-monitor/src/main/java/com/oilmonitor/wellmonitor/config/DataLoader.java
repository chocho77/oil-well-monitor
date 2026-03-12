package com.oilmonitor.wellmonitor.config;

import com.oilmonitor.wellmonitor.model.Well;
import com.oilmonitor.wellmonitor.repository.WellRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final WellRepository wellRepository;

    public DataLoader(WellRepository wellRepository) {
        this.wellRepository = wellRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Проверяваме дали има данни, ако няма - добавяме тестова колонка
        if (wellRepository.count() == 0) {
            Well well = new Well();
            well.setName("Well North-1");
            well.setLocation("Platform A, North Sea");
            well.setStatus("ACTIVE");
            wellRepository.save(well);
            
            System.out.println("Added test well: Well North-1");
        }
    }
}