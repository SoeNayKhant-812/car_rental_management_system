package com.crs.main.controller;

import com.crs.main.dto.CarDTO;
import com.crs.main.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.findAll();
        return ResponseEntity.ok(cars);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        CarDTO car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping("/create")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        CarDTO createdCar = carService.save(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping("/{id}/update")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        CarDTO updatedCar = carService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
