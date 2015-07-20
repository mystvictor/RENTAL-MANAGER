package com.score.data;

import android.os.Build;
import android.support.annotation.NonNull;

import com.score.models.Customer;
import com.score.models.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by myves.stvictor on 2015-06-13.
 */
public class DatabaseHelperFake {

    public static List<Vehicle> Vehicles(){
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        Vehicle vehicle3 = new Vehicle();
        Vehicle vehicle4 = new Vehicle();
        Vehicle vehicle5 = new Vehicle();
        Vehicle vehicle6 = new Vehicle();

        vehicle1.setId(1);
        vehicle1.setMake("BMW");
        vehicle1.setModel("X6");
        vehicle1.setPlate("LOC-1111");
        vehicle1.setRate(350);
        vehicle1.setStatus(true);
        vehicle1.setOwnerName("Eurikar Gouffe");
        vehicle1.setOwnerPhone("5148123313");
        vehicle1.setOwnerEmail("eurikar@hotmail.com");
        vehicles.add(vehicle1);

        vehicle2.setId(2);
        vehicle2.setMake("LAND ROVER");
        vehicle2.setModel("RANGE ROVER");
        vehicle2.setPlate("LOC-2222");
        vehicle2.setRate(450);
        vehicle2.setStatus(true);
        vehicle2.setOwnerName("Marc Yves St-Victor");
        vehicle2.setOwnerPhone("5148121213");
        vehicle2.setOwnerEmail("myves10@hotmail.com");
        vehicles.add(vehicle2);

        vehicle3.setId(3);
        vehicle3.setMake("PORSHE");
        vehicle3.setModel("CAYENNE");
        vehicle3.setPlate("LOC-3333");
        vehicle3.setRate(550);
        vehicle3.setStatus(true);
        vehicle3.setOwnerName("Eurikar Gouffe");
        vehicle3.setOwnerPhone("5148123313");
        vehicle3.setOwnerEmail("eurikar@hotmail.com");
        vehicles.add(vehicle3);

        vehicle4.setId(4);
        vehicle4.setMake("MERCEDES");
        vehicle4.setModel("C63 AMG");
        vehicle4.setPlate("LOC-4444");
        vehicle4.setRate(650);
        vehicle4.setStatus(true);
        vehicle4.setOwnerName("Marc Yves St-Victor");
        vehicle4.setOwnerPhone("5148121213");
        vehicle4.setOwnerEmail("myves10@hotmail.com");
        vehicles.add(vehicle4);

        vehicle5.setId(5);
        vehicle5.setMake("AUDI");
        vehicle5.setModel("A7");
        vehicle5.setPlate("LOC-5555");
        vehicle5.setRate(750);
        vehicle5.setStatus(true);
        vehicle5.setOwnerName("Eurikar Gouffe");
        vehicle5.setOwnerPhone("5148123313");
        vehicle5.setOwnerEmail("eurikar@hotmail.com");
        vehicles.add(vehicle5);

        vehicle6.setId(6);
        vehicle6.setMake("MASSERATI");
        vehicle6.setPlate("LOC-6666");
        vehicle6.setRate(850);
        vehicle6.setStatus(true);
        vehicle6.setOwnerName("Marc Yves St-Victor");
        vehicle6.setOwnerPhone("5148121213");
        vehicle6.setOwnerEmail("myves10@hotmail.com");
        vehicles.add(vehicle6);

        return vehicles;
    };

    public static Vehicle getVehicle(long id, List<Vehicle> inventory){
        Vehicle vehicle = new Vehicle();

        for(Vehicle v : inventory){
            if(v.getId() == id){
                vehicle = v;
            }
        }

        return vehicle;
    }

    public static List<Customer> Customers(){
        List<Customer> customers = new ArrayList<Customer>();

        return customers;
    }
}
