package com.crs.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long vehicleId;
    private String service_type;
    private LocalDate service_date;
    private BigDecimal price;
    private String service_center;
    private String description;
    private LocalDate next_service_due_date;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public LocalDate getService_date() {
        return service_date;
    }

    public void setService_date(LocalDate service_date) {
        this.service_date = service_date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getService_center() {
        return service_center;
    }

    public void setService_center(String service_center) {
        this.service_center = service_center;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getNext_service_due_date() {
        return next_service_due_date;
    }

    public void setNext_service_due_date(LocalDate next_service_due_date) {
        this.next_service_due_date = next_service_due_date;
    }
}
