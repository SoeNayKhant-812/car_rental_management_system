package com.crs.main.controller;

import com.crs.main.dto.LocationDTO;
import com.crs.main.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllCars() {
        List<LocationDTO> cars = locationService.getLocations();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getCarById(@PathVariable Long id) {
        LocationDTO car = locationService.getLocationById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/create")
    public ResponseEntity<LocationDTO> createCar(@RequestBody LocationDTO carDTO) {
        LocationDTO createdCar = locationService.saveLocation(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<LocationDTO> updateCar(@PathVariable Long id,@RequestBody LocationDTO carDTO) {
        LocationDTO updatedCar = locationService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.noContent().build();
    }
}
