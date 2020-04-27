package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class VehicleDao {
    //PersistenceContext annotation used to specify there is a database source.
    //EntityManager is used to create and remove persistent entity instances,
    //to find entities by their primary key, and to query over entities.
    @PersistenceContext
    private EntityManager entityManager;

    public VehicleDao(){

    }

    //Insert VEHICLE into the database.
    public void addVehicle(Vehicle vehicle) {
        entityManager.persist(vehicle);
        return;
    }

    //Return the VEHICLE with the passed-in id.
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }

    public void update(Vehicle vehicle) {
        entityManager.merge(vehicle);
    }

    public void delete(int id) {
        Vehicle v = entityManager.find(Vehicle.class, id);
        entityManager.remove(v);
    }

    public List<Vehicle> getLatestVehicleCount() {
        new ArrayList();
        String queryStr = "SELECT * FROM vehicles ORDER BY id DESC LIMIT 10";
        Query query = this.entityManager.createNativeQuery(queryStr, Vehicle.class);
        List<Vehicle> latestVehicles = query.getResultList();
        return latestVehicles;
    }

}