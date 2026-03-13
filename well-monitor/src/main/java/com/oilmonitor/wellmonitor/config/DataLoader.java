package com.oilmonitor.wellmonitor.config;

import com.oilmonitor.wellmonitor.model.Well;
import com.oilmonitor.wellmonitor.model.SensorReading;
import com.oilmonitor.wellmonitor.repository.WellRepository;
import com.oilmonitor.wellmonitor.repository.SensorReadingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final WellRepository wellRepository;
    private final SensorReadingRepository sensorReadingRepository;

    public DataLoader(WellRepository wellRepository, 
                     SensorReadingRepository sensorReadingRepository) {
        this.wellRepository = wellRepository;
        this.sensorReadingRepository = sensorReadingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Проверяваме дали има данни, ако няма - добавяме тестова колонка
        if (wellRepository.count() == 0) {
            // Създаваме KAVARNA
            Well kavarna = new Well();
            kavarna.setName("KAVARNA");
            kavarna.setLocation("OFFSHORE SUBSEA");
            kavarna.setStatus("ACTIVE");
            wellRepository.save(kavarna);
            
            // Създаваме KALIAKRA
            Well kaliakra = new Well();
            kaliakra.setName("KALIAKRA");
            kaliakra.setLocation("OFFSHORE SUBSEA");
            kaliakra.setStatus("ACTIVE");
            wellRepository.save(kaliakra);
            
            System.out.println("Added test wells: KAVARNA and KALIAKRA");
            
            // Добавяме няколко тестови четения
            Random random = new Random();
            
            // За KAVARNA - 10 тестови четения
            for (int i = 0; i < 10; i++) {
                SensorReading reading = new SensorReading();
                reading.setWell(kavarna);
                reading.setTimestamp(LocalDateTime.now().minusMinutes(i * 5));
                reading.setFlowRate(500000 + random.nextDouble() * 200000);
                reading.setPressure(1200 + random.nextDouble() * 200);
                reading.setTemperature(65 + random.nextDouble() * 15);
                reading.setValvePosition(random.nextDouble() * 100);
                sensorReadingRepository.save(reading);
            }
            
            // За KALIAKRA - 10 тестови четения
            for (int i = 0; i < 10; i++) {
                SensorReading reading = new SensorReading();
                reading.setWell(kaliakra);
                reading.setTimestamp(LocalDateTime.now().minusMinutes(i * 5));
                reading.setFlowRate(680000000 + random.nextDouble() * 10000000);
                reading.setPressure(1300 + random.nextDouble() * 50);
                reading.setTemperature(70 + random.nextDouble() * 10);
                reading.setValvePosition(80 + random.nextDouble() * 20);
                sensorReadingRepository.save(reading);
            }
            
            System.out.println("Added test sensor readings");
        }
    }
}
