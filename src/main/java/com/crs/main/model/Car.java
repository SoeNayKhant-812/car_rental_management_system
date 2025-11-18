package com.crs.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vin;
    private String license_plate;
    private String make;
    private String model;
    private int year;
    private String color;
    private int mileage;
    private BigDecimal daily_rental_rate;
    private long vehicle_type_id;
    private long current_location_id;
    private String booking_status;  // enum can be used for better role management<-----------------------
    private String features;
    private String image_url;

    private Instant created_at;
    private Instant updated_at;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getDaily_rental_rate() {
        return daily_rental_rate;
    }

    public void setDaily_rental_rate(BigDecimal daily_rental_rate) {
        this.daily_rental_rate = daily_rental_rate;
    }

    public long getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(long vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }

    public long getCurrent_location_id() {
        return current_location_id;
    }

    public void setCurrent_location_id(long current_location_id) {
        this.current_location_id = current_location_id;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
    }
}
