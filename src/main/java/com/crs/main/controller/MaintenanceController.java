package com.crs.main.controller;

import com.crs.main.dto.MaintenanceDTO;
import com.crs.main.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<MaintenanceDTO>> getAllMaintenances() {
        List<MaintenanceDTO> cars = maintenanceService.getAllMaintenances();
        return ResponseEntity.ok(cars);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        MaintenanceDTO car = maintenanceService.getMaintenanceById(id);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping("/create")
    public ResponseEntity<MaintenanceDTO> createMaintenance(@RequestBody MaintenanceDTO carDTO) {
        MaintenanceDTO createdCar = maintenanceService.saveMaintenance(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping("/{id}/update")
    public ResponseEntity<MaintenanceDTO> updateMaintenance(@PathVariable Long id, @RequestBody MaintenanceDTO carDTO) {
        MaintenanceDTO updatedCar = maintenanceService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenanceById(id);
        return ResponseEntity.noContent().build();
    }
}
