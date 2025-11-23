package com.crs.main.mapper;

import com.crs.main.dto.CarDTO;
import com.crs.main.model.Car;

import java.time.Instant;

public class CarMapper {
    public static CarDTO toDTO(Car car) {
        if (car == null) {
            return null;
        }
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setVin(car.getVin());
        dto.setLicense_plate(car.getLicense_plate());
        dto.setMake(car.getMake());
        dto.setModel(car.getModel());
        dto.setPrice(car.getPrice());
        dto.setType(car.getType());
        dto.setYear(car.getYear());
        dto.setColor(car.getColor());
        dto.setMileage(car.getMileage());
        dto.setDaily_rental_rate(car.getDaily_rental_rate());
        dto.setVehicle_type_id(car.getVehicle_type_id());
        dto.setCurrent_location_id(car.getCurrent_location_id());
        dto.setStatus(car.getStatus());
        dto.setFeatures(car.getFeatures());
        dto.setImage_url(car.getImage_url());
        return dto;
    }

    public static Car toEntity(CarDTO dto) {
        if (dto == null) {
            return null;
        }
        Car car = new Car();
        car.setId(dto.getId());
        car.setVin(dto.getVin());
        car.setLicense_plate(dto.getLicense_plate());
        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        car.setPrice(dto.getPrice());
        car.setType(dto.getType());
        car.setYear(dto.getYear());
        car.setColor(dto.getColor());
        car.setMileage(dto.getMileage());
        car.setDaily_rental_rate(dto.getDaily_rental_rate());
        car.setVehicle_type_id(dto.getVehicle_type_id());
        car.setCurrent_location_id(dto.getCurrent_location_id());
        car.setStatus(dto.getStatus());
        car.setFeatures(dto.getFeatures());
        car.setImage_url(dto.getImage_url());
        car.setCreated_at(Instant.now());
        car.setUpdated_at(Instant.now());
        return car;
    }
}
