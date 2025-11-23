package com.crs.main.mapper;

import com.crs.main.dto.BookingDTO;
import com.crs.main.model.Booking;

import java.time.Instant;

public class BookingMapper {
    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setUser_id(booking.getUser_id());
        bookingDTO.setVehicle_id(booking.getVehicle_id());
        bookingDTO.setPickup_datetime(booking.getPickup_datetime());
        bookingDTO.setDropOff_datetime(booking.getDropOff_datetime());
        bookingDTO.setPickup_location_id(booking.getPickup_location_id());
        bookingDTO.setDropOff_location_id(booking.getDropOff_location_id());
        bookingDTO.setEstimated_total_price(booking.getEstimated_total_price());
        bookingDTO.setActual_total_price(booking.getActual_total_price());
        bookingDTO.setBooking_status(booking.getBooking_status());
        bookingDTO.setBooking_type(booking.getBooking_type());
        bookingDTO.setNotes(booking.getNotes());
        return bookingDTO;
    }

    public static Booking toEntity(BookingDTO dto) {
        if (dto == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setUser_id(dto.getUser_id());
        booking.setVehicle_id(dto.getVehicle_id());
        booking.setPickup_datetime(dto.getPickup_datetime());
        booking.setDropOff_datetime(dto.getDropOff_datetime());
        booking.setPickup_location_id(dto.getPickup_location_id());
        booking.setDropOff_location_id(dto.getDropOff_location_id());
        booking.setEstimated_total_price(dto.getEstimated_total_price());
        booking.setActual_total_price(dto.getActual_total_price());
        booking.setBooking_status(dto.getBooking_status());
        booking.setBooking_type(dto.getBooking_type());
        booking.setNotes(dto.getNotes());
        booking.setCreated_at(Instant.now());
        booking.setUpdated_at(Instant.now());
        return booking;
    }
}
