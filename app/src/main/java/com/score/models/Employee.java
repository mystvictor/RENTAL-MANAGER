package com.score.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by myves.stvictor on 2015-06-17.
 */
public class Employee {
    private long ID;
    private String fullName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private int profile;
    private Date accountExpirationDate;

    public Employee() { }

    public Employee(String fullName, String username, String password, String phone, String email,
                    int profile, Date accountExpirationDate) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.profile = profile;
        this.accountExpirationDate = accountExpirationDate;
    }

    public Employee(long ID, String fullName, String username, String password, String phone,
                    String email, int profile, Date accountExpirationDate) {
        this.ID = ID;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.profile = profile;
        this.accountExpirationDate = accountExpirationDate;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public Date getAccountExpirationDate() {
        return accountExpirationDate;
    }

    public void setAccountExpirationDate(Date accountExpirationDate) {
        this.accountExpirationDate = accountExpirationDate;
    }

    public String  getAccountExpirationDateToString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateToSring = simpleDateFormat.format(this.accountExpirationDate);

        return dateToSring;
    }

    public Date getAccountExpirationStringToDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date stringToDate = null;
        try {
            stringToDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stringToDate;
    }
}
