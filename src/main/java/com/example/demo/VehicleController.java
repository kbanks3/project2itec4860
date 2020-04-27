package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class VehicleController {
    @Autowired //we will cover this later
    private VehicleDao vehicleDao;

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody int id, String make, int year, int price) throws IOException {
        Vehicle newVehicle = new Vehicle(id, make, year, price);
        vehicleDao.addVehicle(newVehicle);
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        return vehicleDao.getById(id);
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle v) throws IOException {
        vehicleDao.update(v);
        return v;
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        vehicleDao.delete(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/getLatestVehicleCount", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicleCount() throws IOException {
        return vehicleDao.getLatestVehicleCount();
    }

}