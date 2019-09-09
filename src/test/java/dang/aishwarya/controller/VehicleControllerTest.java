package dang.aishwarya.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dang.aishwarya.entity.Vehicle;
import dang.aishwarya.repository.VehicleRepository;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@ActiveProfiles("integrationtest")
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VehicleRepository repository;

    @Before
    public void setup() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVin("1HGCR2F3XFA027534");
        vehicle1.setMake("HONDA");
        vehicle1.setModel("ACCORD");
        vehicle1.setYear(2015);
        vehicle1.setRedlineRpm(5500);
        vehicle1.setMaxFuelVolume(15);
        vehicle1.setLastServiceDate("2017-05-25T17:31:25.268Z");

        repository.save(vehicle1);
    }

    @After
    public void destroy() {
        repository.deleteAll();
    }

    @Test
    public void findAll() throws Exception{

        mvc.perform(MockMvcRequestBuilders.get("/vehicles"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].model", Matchers.is("ACCORD")));

    }

    @Test
    public void findOne() {
    }

    @Test
    public void create() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVin("1HGCR2F3XFA027535");
        vehicle1.setMake("HONDA1");
        vehicle1.setModel("ACCORD1");
        vehicle1.setYear(20151);
        vehicle1.setRedlineRpm(55001);
        vehicle1.setMaxFuelVolume(151);
        vehicle1.setLastServiceDate("2018-05-25T17:31:25.268Z");

        Vehicle[] vehicles = new Vehicle[] {vehicle1};

        mvc.perform(MockMvcRequestBuilders.put("/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(vehicles))
        )
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].model", Matchers.is("ACCORD1")));
    }
}