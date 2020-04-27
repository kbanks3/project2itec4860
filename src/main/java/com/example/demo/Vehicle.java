package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;
    private int year;
    private int price;

    public Vehicle() {
        //this.id = 0;
    }

    public Vehicle(int id, String make, int year, int price) {
        this.id = id;
        this.make = make;
        this.year = year;
        this.price = price;
    }

    public Vehicle(String make, int year, int price) {
        this.id = id;
        this.make = make;
        this.year = year;
        this.price = price;
    }

    public String toString() {
        return "{" + "\"id\":" + id + ",\"make\":" + make + ",\"year\":" + year + ",\"price\":" + price + "}";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}