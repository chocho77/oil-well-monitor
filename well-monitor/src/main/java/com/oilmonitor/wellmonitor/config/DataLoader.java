package com.oilmonitor.wellmonitor.config;

import com.oilmonitor.wellmonitor.model.Well;
import com.oilmonitor.wellmonitor.model.Alarm;
import com.oilmonitor.wellmonitor.repository.WellRepository;
import com.oilmonitor.wellmonitor.repository.AlarmRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
//import java.time.LocalTime;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final WellRepository wellRepository;
    private final AlarmRepository alarmRepository;

    public DataLoader(WellRepository wellRepository, AlarmRepository alarmRepository) {
        this.wellRepository = wellRepository;
        this.alarmRepository = alarmRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Проверяваме дали има данни, ако няма - добавяме тестови данни
        if (wellRepository.count() == 0) {
            createWells();
            createAlarms();
        }
    }
    
    private void createWells() {
        Random random = new Random();
        
        // ----- KAVARNA -----
        Well kavarna = new Well();
        kavarna.setName("KAVARNA");
        kavarna.setLocation("OFFSHORE SUBSEA");
        kavarna.setStatus("ACTIVE");
        
        // Налягания
        kavarna.setPt3101(1327.2); // PSIG
        kavarna.setPt1105(7.2); // Bar(G)
        kavarna.setPt1205(38.4); // Bar(G)
        kavarna.setPt1401(1343.2); // PSIG
        kavarna.setDp001(0.8); // Bar
        
        // Клапани
        kavarna.setSdv3001("FROM WHCP / FROM CHEM INJ PUMPS");
        kavarna.setSdv4001("FROM WHCP / FROM CHEM INJ PUMPS");
        kavarna.setSdv1203(39.1); // Bar(G)
        
        // Позиции
        kavarna.setZt1100(8.9); // %
        kavarna.setZt1200(0.0); // %
        
        // Вибрации
        kavarna.setV460Vibration(0.5 + random.nextDouble() * 2.0);
        kavarna.setV410Vibration(0.3 + random.nextDouble() * 1.5);
        
        // Компресори
        kavarna.setC460(95.5 + random.nextDouble() * 4.5);
        kavarna.setC410(97.2 + random.nextDouble() * 2.8);
        
        // Дебит
        kavarna.setCurrentDayTotalisedFlow(0.0); // 0 RM3
        kavarna.setPreviousDayTotalisedFlow(0.0); // 0 RM3
        
        // Температура
        kavarna.setTt1100(5.4); // DegC
        
        // Bypass
        kavarna.setPt1100lltrpBypass(false);
        kavarna.setPt1200lltrpBypass(false);
        kavarna.setBypassTimeSeconds(5000); // 50,00.00 SEC
        
        wellRepository.save(kavarna);
        
        // ----- KALIAKRA -----
        Well kaliakra = new Well();
        kaliakra.setName("KALIAKRA");
        kaliakra.setLocation("OFFSHORE SUBSEA");
        kaliakra.setStatus("ACTIVE");
        
        // Налягания - подобни на KAVARNA
        kaliakra.setPt3101(1327.2);
        kaliakra.setPt1105(7.2);
        kaliakra.setPt1205(38.4);
        kaliakra.setPt1401(1343.2);
        kaliakra.setDp001(0.8);
        
        // Клапани
        kaliakra.setSdv3001("FROM WHCP / FROM CHEM INJ PUMPS");
        kaliakra.setSdv4001("FROM WHCP / FROM CHEM INJ PUMPS");
        kaliakra.setSdv1203(39.1);
        
        // Позиции
        kaliakra.setZt1100(8.9);
        kaliakra.setZt1200(0.0);
        
        // Вибрации
        kaliakra.setV460Vibration(0.6 + random.nextDouble() * 2.2);
        kaliakra.setV410Vibration(0.4 + random.nextDouble() * 1.8);
        
        // Компресори
        kaliakra.setC460(94.8 + random.nextDouble() * 5.2);
        kaliakra.setC410(96.5 + random.nextDouble() * 3.5);
        
        // Дебит - KALIAKRA има голям дебит!
        kaliakra.setCurrentDayTotalisedFlow(684772608.0); // 684,772,608 RM3
        kaliakra.setPreviousDayTotalisedFlow(0.0);
        
        // Температура
        kaliakra.setTt1100(5.4);
        
        // Bypass
        kaliakra.setPt1100lltrpBypass(false);
        kaliakra.setPt1200lltrpBypass(false);
        kaliakra.setBypassTimeSeconds(5000);
        
        wellRepository.save(kaliakra);
        
        System.out.println("✅ Added test wells: KAVARNA and KALIAKRA with all sensors");
    }
    
    private void createAlarms() {
        // Създаваме алармите от снимката
        
        // 1. TEQ REBOILER TEMP CONTROLLER
        Alarm alarm1 = new Alarm();
        alarm1.setDateTime(LocalDateTime.of(2025, 2, 26, 10, 55, 0));
        alarm1.setState("UNACK");
        alarm1.setOperator("ONSERV1/Operator");
        alarm1.setPriority(1);
        alarm1.setComment("TEQ REBOILER TEMP CONTROLLER");
        alarm1.setName("TIC_330-A3");
        alarm1.setProvider("WONSERV1InTouch");
        alarm1.setLimitType("LOW");
        alarm1.setSilenced(false);
        alarmRepository.save(alarm1);
        
        // 2. TRANSMITTER - FAULTY
        Alarm alarm2 = new Alarm();
        alarm2.setDateTime(LocalDateTime.of(2025, 2, 26, 10, 54, 33));
        alarm2.setState("UNACK");
        alarm2.setOperator("ONSERV1/Operator");
        alarm2.setPriority(1);
        alarm2.setComment("TRANSMITTER - FAULTY");
        alarm2.setName("GDP0004_Q-A1");
        alarm2.setProvider("WONSERV1InTouch");
        alarm2.setLimitType("DISCRETE");
        alarm2.setSilenced(false);
        alarmRepository.save(alarm2);
        
        // 3. ANY Primary Data Path
        Alarm alarm3 = new Alarm();
        alarm3.setDateTime(LocalDateTime.of(2025, 2, 26, 10, 20, 44));
        alarm3.setState("UNACK");
        alarm3.setOperator("ONENG/Engineer");
        alarm3.setPriority(1);
        alarm3.setComment("ANY Primary Data Path");
        alarm3.setName("SERV_OK");
        alarm3.setProvider("UnTouch");
        alarm3.setLimitType("Dead");
        alarm3.setSilenced(false);
        alarmRepository.save(alarm3);
        
        // 4. Cluster 2 Primary Tag Server
        Alarm alarm4 = new Alarm();
        alarm4.setDateTime(LocalDateTime.of(2025, 2, 26, 10, 20, 44));
        alarm4.setState("UNACK");
        alarm4.setOperator("ONENG/Engineer");
        alarm4.setPriority(1);
        alarm4.setComment("Cluster 2 Primary Tag Server");
        alarm4.setName("SERV_OK");
        alarm4.setProvider("UnTouch");
        alarm4.setLimitType("Dead");
        alarm4.setSilenced(false);
        alarmRepository.save(alarm4);
        
        // 5. ANY Backup Data Path
        Alarm alarm5 = new Alarm();
        alarm5.setDateTime(LocalDateTime.of(2025, 2, 26, 10, 20, 44));
        alarm5.setState("UNACK");
        alarm5.setOperator("ONENG/Engineer");
        alarm5.setPriority(1);
        alarm5.setComment("ANY Backup Data Path");
        alarm5.setName("SERV_OK");
        alarm5.setProvider("UnTouch");
        alarm5.setLimitType("Dead");
        alarm5.setSilenced(false);
        alarmRepository.save(alarm5);
        
        System.out.println("✅ Added 5 test alarms from SCADA screen");
    }
}
