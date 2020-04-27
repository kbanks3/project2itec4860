package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@EnableScheduling
@Component
public class ScheduledTasks {
    private RestTemplate restTemplate = new RestTemplate();
    private int id = 1;

    @Scheduled(fixedRate = 3000)
    public void addVehicle() {
        String postURL = "http://localhost:3306/addVehicle";
        Vehicle newVehicle = new Vehicle(RandomStringUtils.randomAlphabetic(10),
                RandomUtils.nextInt(1986, 2016), RandomUtils.nextInt(15000, 45000));
        restTemplate.postForObject(postURL, newVehicle, Vehicle.class);
    }
//        int year = RandomUtils.nextInt(1986, 2017);
//        int retailPrice = RandomUtils.nextInt(15000, 45001);
//        String makeModel = RandomStringUtils.randomAlphabetic(5, 10);
//        Vehicle v = new Vehicle(id++, makeModel, year, retailPrice);
//        restTemplate.postForLocation(postURL, v);
//        System.out.println("Added vehicle");
//    }

    @Scheduled(fixedRate = 9000)
    public void deleteVehicle() {
        int id = RandomUtils.nextInt(0, 101);
        String postURL = "http://localhost:3306/deleteVehicle/" + id;
        restTemplate.delete(postURL);
        System.out.println("Deleted Vehicle");
    }

    //vehicle will wait for spring boot to initialize then add vehicles
    //then vehicles will be deleted
    //now they must be updated every 2 min
    @Scheduled(cron = "* 2 * * * *")
    public void updateVehicle() {
        String postURL = "http://localhost:3306/updateVehicle";
        int id = RandomUtils.nextInt(0, 101);
        Vehicle v = new Vehicle(id, RandomStringUtils.randomAlphabetic(10),
                RandomUtils.nextInt(1986, 2016), RandomUtils.nextInt(15000, 45000));
        restTemplate.put(postURL, v);
        System.out.println("Updated vehicle");
    }

    //every hour top of the hour
    @Scheduled(cron = "* 0 0 * * *")
    public void getLatestVehicleCount() {
        String getURL = "http://localhost:3306/getLatestVehicleCount/";
        List<Vehicle> vehicles = restTemplate.getForObject(getURL, List.class);
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            System.out.println(vehicles.get(i));
        }
    }

}
