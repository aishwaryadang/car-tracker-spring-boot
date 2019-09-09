package dang.aishwarya.controller;

import dang.aishwarya.entity.Vehicle;
import dang.aishwarya.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vehicles")
@Api(description = "Vehicle Endpoints")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ApiOperation(value = "GET All Vehicles")
    @RequestMapping(method = RequestMethod.GET)
    //don't need for project
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

    //Don't need for project
    @RequestMapping(method = RequestMethod.GET, value = "/{vin}")
    public Vehicle findOne(@PathVariable("vin") String vehicleID) {
        return null;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public Vehicle[] create(@RequestBody Vehicle[] vehicle) {
        for(Vehicle vehicleEach : vehicle)
        vehicleService.create(vehicleEach);

        return vehicle;
    }
}
