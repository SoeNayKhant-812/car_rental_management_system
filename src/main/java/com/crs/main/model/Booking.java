package com.crs.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long user_id;
    private long vehicle_id;
    private LocalDateTime pickup_datetime;
    private LocalDateTime dropOff_datetime;
    private long pickup_location_id;
    private long dropOff_location_id;
    private BigDecimal estimated_total_price;
    private BigDecimal actual_total_price;
    private long booking_status;  // enum can be used for better role management<-----------------------
    private long payment_status;  // enum can be used for better role management<-----------------------
    private String notes;
    private Instant created_at;
    private Instant updated_at;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public LocalDateTime getPickup_datetime() {
        return pickup_datetime;
    }

    public void setPickup_datetime(LocalDateTime pickup_datetime) {
        this.pickup_datetime = pickup_datetime;
    }

    public LocalDateTime getDrpOff_datetime() {
        return dropOff_datetime;
    }

    public void setDropOff_datetime(LocalDateTime dropOff_datetime) {
        this.dropOff_datetime = dropOff_datetime;
    }

    public long getPickup_location_id() {
        return pickup_location_id;
    }

    public void setPickup_location_id(long pickup_location_id) {
        this.pickup_location_id = pickup_location_id;
    }

    public long getDropOff_location_id() {
        return dropOff_location_id;
    }

    public void setDropOff_location_id(long dropOff_location_id) {
        this.dropOff_location_id = dropOff_location_id;
    }

    public BigDecimal getEstimated_total_price() {
        return estimated_total_price;
    }

    public void setEstimated_total_price(BigDecimal estimated_total_price) {
        this.estimated_total_price = estimated_total_price;
    }

    public BigDecimal getActual_total_price() {
        return actual_total_price;
    }

    public void setActual_total_price(BigDecimal actual_total_price) {
        this.actual_total_price = actual_total_price;
    }

    public long getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(long booking_status) {
        this.booking_status = booking_status;
    }

    public long getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(long payment_status) {
        this.payment_status = payment_status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
