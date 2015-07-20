package com.score.models;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class Vehicle {
    private long id;
    private String make;
    private String model;
    private String plate;
    private double rate;
    private int status;
    private String ownerName;
    private String ownerPhone;
    private String ownerEmail;

    public Vehicle() { }

    public Vehicle(String make, String model, String plate) {
        this.make = make;
        this.model = model;
        this.plate = plate;
    }

    public Vehicle(String make, String model, String plate, double rate, int status) {
        this.make = make;
        this.model = model;
        this.plate = plate;
        this.rate = rate;
        this.status = status;
    }

    public Vehicle(long id, String make, String model, String plate, double rate, int status) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.plate = plate;
        this.rate = rate;
        this.status = status;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }
}
