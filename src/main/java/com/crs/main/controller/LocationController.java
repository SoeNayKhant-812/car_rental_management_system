package com.crs.main.controller;

import com.crs.main.dto.LocationDTO;
import com.crs.main.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> cars = locationService.getLocations();
        return ResponseEntity.ok(cars);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        LocationDTO car = locationService.getLocationById(id);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @PostMapping("/create")
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO carDTO) {
        LocationDTO createdCar = locationService.saveLocation(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','STAFF')")
    @PutMapping("/{id}/update")
    public ResponseEntity<LocationDTO> updateLocation(@PathVariable Long id, @RequestBody LocationDTO carDTO) {
        LocationDTO updatedCar = locationService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.noContent().build();
    }
}
