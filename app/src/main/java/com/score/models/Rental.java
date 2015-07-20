package com.score.models;

import java.lang.ref.SoftReference;
import java.util.Date;

/**
 * Created by myves.stvictor on 2015-06-17.
 */
public class Rental {
    private long id;
    private long customer_id;
    private long vehicle_id;
    private String startDate;
    private String returnDate;
    private Double total;

    public Rental() { }

    public Rental(long customer_id, long vehicle_id, String startDate, String returnDate, Double total) {
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.total = total;
    }

    public Rental(long id, long customer_id, long vehicle_id, String startDate, String returnDate, Double total) {
        this.id = id;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
