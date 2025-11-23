package com.crs.main.model;

import com.crs.main.util.enums.CarUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vin;
    private String license_plate;
    private String make;
    private String model;
    private double price;
    private CarUtils.CarType type; // enum used
    private int year;
    private String color;
    private int mileage;
    private double daily_rental_rate;
    private long vehicle_type_id;
    private long current_location_id;
    private CarUtils.CarStatus status; // enum used
    private String features;
    private String image_url;

    private Instant created_at;
    private Instant updated_at;
}
