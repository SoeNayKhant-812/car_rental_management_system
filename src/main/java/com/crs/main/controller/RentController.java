package com.crs.main.controller;

import com.crs.main.dto.RentDTO;
import com.crs.main.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rents")
public class RentController {
    private final RentService rentService;

    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @GetMapping
    public ResponseEntity<List<RentDTO>> getAllRents() {
        List<RentDTO> cars = rentService.findAll();
        return ResponseEntity.ok(cars);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<RentDTO> getRentById(@PathVariable Long id) {
        RentDTO car = rentService.findById(id);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @PostMapping("/create")
    public ResponseEntity<RentDTO> createRent(@RequestBody RentDTO carDTO) {
        RentDTO createdCar = rentService.save(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @PutMapping("/{id}/update")
    public ResponseEntity<RentDTO> updateRent(@PathVariable Long id, @RequestBody RentDTO carDTO) {
        RentDTO updatedCar = rentService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','USER')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
