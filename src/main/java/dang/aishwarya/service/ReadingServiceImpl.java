package dang.aishwarya.service;

import dang.aishwarya.entity.Alerts;
import dang.aishwarya.entity.Readings;
import dang.aishwarya.entity.Vehicle;
import dang.aishwarya.repository.AlertsRepository;
import dang.aishwarya.repository.ReadingRepository;
import dang.aishwarya.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ReadingRepository readingRepository;

    @Autowired
    AlertsRepository alertsRepository;

    @Override
    @Transactional
    public Readings updateReadings(Readings readings) {
        Optional<Vehicle> readingsOfVehicle = vehicleRepository.findById(readings.getVin()); //em.find(Vehicle.class, readings.getVin());
        if (readingsOfVehicle.isPresent()) {

            if (readings.getEngineRpm() > readingsOfVehicle.get().getRedlineRpm())
                alertsRepository.save(new Alerts("engineRpm > redlineRpm", "HIGH", readingsOfVehicle.get(), readings.getTimestamp()));
            if (readings.getFuelVolume() < (readingsOfVehicle.get().getMaxFuelVolume() / 10))
                alertsRepository.save(new Alerts("fuelVolume < 10% of maxFuelVolume", "MEDIUM", readingsOfVehicle.get(), readings.getTimestamp()));
            if (32 > readings.getFrontLeft() || 32 > readings.getFrontRight() || 32 > readings.getRearLeft() || 32 > readings.getRearRight())
                alertsRepository.save(new Alerts("Tire Pressure Low", "LOW", readingsOfVehicle.get(), readings.getTimestamp()));
            if (36 < readings.getFrontLeft() || 36 < readings.getFrontRight() || 36 < readings.getRearLeft() || 36 < readings.getRearRight())
                alertsRepository.save(new Alerts("Tire Pressure High", "LOW", readingsOfVehicle.get(), readings.getTimestamp()));
            if (readings.isEngineCoolantLow())
                alertsRepository.save(new Alerts("Engine Coolant Low", "LOW", readingsOfVehicle.get(), readings.getTimestamp()));
            if (readings.isCheckEngineLightOn())
                alertsRepository.save(new Alerts("Check Engine Light is On", "LOW", readingsOfVehicle.get(), readings.getTimestamp()));
        }

        return readingRepository.save(readings);
    }
}
