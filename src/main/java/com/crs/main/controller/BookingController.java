package com.crs.main.controller;

import com.crs.main.dto.BookingDTO;
import com.crs.main.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllCars() {
        List<BookingDTO> cars = bookingService.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getCarById(@PathVariable Long id) {
        BookingDTO car = bookingService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createCar(@RequestBody BookingDTO carDTO) {
        BookingDTO createdCar = bookingService.save(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<BookingDTO> updateCar(@PathVariable Long id,@RequestBody BookingDTO carDTO) {
        BookingDTO updatedCar = bookingService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
