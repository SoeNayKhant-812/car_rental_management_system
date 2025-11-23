package com.crs.main.mapper;

import com.crs.main.dto.RentDTO;
import com.crs.main.model.Rent;

import java.time.Instant;

public class RentMapper {
    public static RentDTO toDTO(Rent rent) {
        if (rent == null) {
            return null;
        }
        RentDTO rentDTO = new RentDTO();
        rentDTO.setId(rent.getId());
        rentDTO.setUser_id(rent.getUser_id());
        rentDTO.setVehicle_id(rent.getVehicle_id());
        rentDTO.setPickup_datetime(rent.getPickup_datetime());
        rentDTO.setDropOff_datetime(rent.getDropOff_datetime());
        rentDTO.setPickup_location_id(rent.getPickup_location_id());
        rentDTO.setDropOff_location_id(rent.getDropOff_location_id());
        rentDTO.setEstimated_total_price(rent.getEstimated_total_price());
        rentDTO.setActual_total_price(rent.getActual_total_price());
        rentDTO.setRent_type(rent.getRent_type());
        rentDTO.setPayment_plan(rent.getPayment_plan());
        rentDTO.setPayment_status(rent.getPayment_status());
        rentDTO.setRental_status(rent.getRental_status());
        rentDTO.setPayments(rent.getPayments());
        rentDTO.setNotes(rent.getNotes());
        return rentDTO;
    }

    public static Rent toEntity(RentDTO rentDTO) {
        if (rentDTO == null) {
            return null;
        }
        Rent rent = new Rent();
        rent.setId(rentDTO.getId());
        rent.setUser_id(rentDTO.getUser_id());
        rent.setVehicle_id(rentDTO.getVehicle_id());
        rent.setPickup_datetime(rentDTO.getPickup_datetime());
        rent.setDropOff_datetime(rentDTO.getDropOff_datetime());
        rent.setPickup_location_id(rentDTO.getPickup_location_id());
        rent.setDropOff_location_id(rentDTO.getDropOff_location_id());
        rent.setEstimated_total_price(rentDTO.getEstimated_total_price());
        rent.setActual_total_price(rentDTO.getActual_total_price());
        rent.setRent_type(rentDTO.getRent_type());
        rent.setPayment_plan(rentDTO.getPayment_plan());
        rent.setPayment_status(rentDTO.getPayment_status());
        rent.setRental_status(rentDTO.getRental_status());
        rent.setPayments(rentDTO.getPayments());
        rent.setNotes(rentDTO.getNotes());
        rent.setCreated_at(Instant.now());
        rent.setUpdated_at(Instant.now());
        return rent;
    }
}
