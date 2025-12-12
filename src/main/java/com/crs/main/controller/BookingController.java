package com.crs.main.controller;

import com.crs.main.dto.BookingDTO;
import com.crs.main.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> cars = bookingService.findAll();
        return ResponseEntity.ok(cars);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        BookingDTO car = bookingService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO carDTO) {
        BookingDTO createdCar = bookingService.save(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @PutMapping("/{id}/update")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO carDTO) {
        BookingDTO updatedCar = bookingService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
