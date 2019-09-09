package dang.aishwarya.service;

import dang.aishwarya.entity.Vehicle;
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
public class VehicleServiceImplTest {

    @TestConfiguration
    static class  VehicleServiceImplTestConfiguration {
        @Bean
        public VehicleService getService() {
            return new VehicleServiceImpl();
        }
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    private List<Vehicle> vehicleList;
    @Before
    public void setup() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("1HGCR2F3XFA027534");
        vehicle.setMake("HONDA");
        vehicle.setModel("ACCORD");
        vehicle.setYear(2015);
        vehicle.setRedlineRpm(5500);
        vehicle.setMaxFuelVolume(15);
        vehicle.setLastServiceDate("2017-05-25T17:31:25.268Z");

        vehicleList = Collections.singletonList(vehicle);

        Mockito.when(vehicleRepository.findAll())
                .thenReturn(vehicleList);

        Mockito.when(vehicleRepository.save(vehicle))
                .thenReturn(vehicle);
    }

    @After
    public void destroy() {

    }

    @Test
    public void findAll() {
        List<Vehicle> result = vehicleService.findAll();

        Assert.assertEquals("Vehicle list doesn't match", vehicleList, result);
    }

    @Test
    public void findOne() {

    }

    @Test
    public void create() {
        Vehicle result = vehicleService.create(vehicleList.get(0));

        Assert.assertEquals("Not Created", vehicleList.get(0), result);
    }
}