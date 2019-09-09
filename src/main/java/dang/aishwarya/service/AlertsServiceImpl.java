package dang.aishwarya.service;

import dang.aishwarya.entity.Alerts;
import dang.aishwarya.repository.AlertsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class AlertsServiceImpl implements AlertsService {

    @Autowired
    AlertsRepository alertsRepository;

    @Override
    public List<Alerts> findHighAlerts() {
        return alertsRepository.findByPriority("HIGH");
    }

    @Override
    public List<Alerts> findHighSorted() throws ParseException {
        List<Alerts> initialList = alertsRepository.findByPriority("HIGH");
        Collections.sort(initialList, new Comparator<Alerts>() {
            @Override
            public int compare(Alerts o1, Alerts o2) {
                if (o1.getVehicle().getVin().compareTo(o2.getVehicle().getVin()) > 0) return -1;
                if (o1.getVehicle().getVin().compareTo(o2.getVehicle().getVin()) < 0) return 1;
                return 0;
            }
        });
        List<Alerts> finalList = new ArrayList<>();
        for(Alerts alert:initialList) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date dt = sdf.parse(alert.getTimestamp());
            long epoch = dt.getTime();
            //System.out.println(epoch);

            long now = Instant.now().toEpochMilli() + 14400000;             // Needed to adjust this otherwise Instant.now() gives current system time.
            //System.out.println(now);
            //System.out.println(now-epoch);
            if(now - epoch< 7200000)
                finalList.add(alert);

        }

        return finalList;
    }

    @Override
    public List<Alerts> findVehicleAlerts(String vehicleID) {
        List<Alerts> alertsList = new ArrayList<>();
        for(Alerts alert : alertsRepository.findAll()){
            if(alert.getVehicle().getVin().equals(vehicleID))
                alertsList.add(alert);
        }
        return alertsList;
    }
}
