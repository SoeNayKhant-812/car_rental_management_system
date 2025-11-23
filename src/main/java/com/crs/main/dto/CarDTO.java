package com.crs.main.dto;

import com.crs.main.util.enums.CarUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CarDTO {
    private long id;

    private String vin;
    private String license_plate;
    private String make;
    private String model;
    private BigDecimal price;
    private CarUtils.CarType type; // enum used
    private int year;
    private String color;
    private int mileage;
    private BigDecimal daily_rental_rate;
    private long vehicle_type_id;
    private long current_location_id;
    private CarUtils.CarStatus status; // enum used
    private String features;
    private String image_url;
}
