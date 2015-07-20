package com.score.models;

/**
 * Created by myves.stvictor on 2015-06-12.
 */
public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private int streetNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private String cellPhone;
    private String email;
    private String driverLicence;

    public Customer() { }

    public Customer(String firstName, String lastName, String cellPhone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhone = cellPhone;
        this.email = email;
    }

    public Customer(String firstName, String lastName, int streetNumber, String streetAddress,
                    String city, String state, String country, String cellPhone, String email,
                    String driverLicence) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetNumber = streetNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.cellPhone = cellPhone;
        this.email = email;
        this.driverLicence = driverLicence;
    }

    public Customer(long id, String firstName, String lastName, int streetNumber,
                    String streetAddress, String city, String state, String country,
                    String cellPhone, String email, String driverLicence) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetNumber = streetNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.cellPhone = cellPhone;
        this.email = email;
        this.driverLicence = driverLicence;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDriverLicence() {
        return driverLicence;
    }

    public void setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
    }
}
