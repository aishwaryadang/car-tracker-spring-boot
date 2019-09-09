package dang.aishwarya.service;

import dang.aishwarya.entity.Alerts;
import dang.aishwarya.entity.Readings;
import dang.aishwarya.entity.Vehicle;
import dang.aishwarya.repository.AlertsRepository;
import dang.aishwarya.repository.ReadingRepository;
import dang.aishwarya.repository.VehicleRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ReadingServiceImplTest {

    @TestConfiguration
    static class ReadingServiceImplTestConfirguration {
        @Bean
        public ReadingService getService() {
            return new ReadingServiceImpl();
        }
    }

    @Autowired
    private ReadingService service;

    @MockBean
    private ReadingRepository repository;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private AlertsRepository alertsRepository;

    private List<Readings> readingsList;

    @Before
    public void setup() {
        Readings reading = new Readings();
        reading.setVin("1HGCR2F3XFA027534");
        reading.setLatitude(41.803194);
        reading.setLongitude(-88.144406);
        reading.setTimestamp("2017-05-25T17:31:25.268Z");
        reading.setFuelVolume(1.5);
        reading.setSpeed(85);
        reading.setEngineHp(240);
        reading.setCheckEngineLightOn(false);
        reading.setEngineCoolantLow(true);
        reading.setCruiseControlOn(true);
        reading.setEngineRpm(6300);
        reading.setFrontLeft(34);
        reading.setFrontRight(36);
        reading.setRearLeft(29);
        reading.setRearRight(34);

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("1HGCR2F3XFA027534");
        vehicle.setMake("HONDA");
        vehicle.setModel("ACCORD");
        vehicle.setYear(2015);
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        vehicle.setLastServiceDate("2017-05-25T17:31:25.268Z");

        readingsList = Collections.singletonList(reading);

        Mockito.when(repository.save(reading))
                .thenReturn(reading);

        Mockito.when(vehicleRepository.findById(reading.getVin()))
                .thenReturn(java.util.Optional.of(vehicle));
        Mockito.when(alertsRepository.save(null)).thenReturn(true);
    }

    @After
    public void destroy() {

    }

    @Test
    public void updateReadings() {
        Readings result = service.updateReadings(readingsList.get(0));

        Assert.assertEquals("Not Equal", readingsList.get(0), result);
    }
}