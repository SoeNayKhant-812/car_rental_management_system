package com.crs.main.controller;

import com.crs.main.dto.VehicleTypeDTO;
import com.crs.main.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-types")
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    @Autowired
    public VehicleTypeController(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping
    public ResponseEntity<List<VehicleTypeDTO>> getAllVehicleTypes() {
        List<VehicleTypeDTO> cars = vehicleTypeService.getVehicleTypes();
        return ResponseEntity.ok(cars);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeDTO> getVehicleTypeById(@PathVariable Long id) {
        VehicleTypeDTO car = vehicleTypeService.getVehicleTypeById(id);
        return ResponseEntity.ok(car);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping("/create")
    public ResponseEntity<VehicleTypeDTO> createVehicleType(@RequestBody VehicleTypeDTO carDTO) {
        VehicleTypeDTO createdCar = vehicleTypeService.saveVehicleType(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping("/{id}/update")
    public ResponseEntity<VehicleTypeDTO> updateVehicleType(@PathVariable Long id, @RequestBody VehicleTypeDTO carDTO) {
        VehicleTypeDTO updatedCar = vehicleTypeService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable Long id) {
        vehicleTypeService.deleteVehicleTypeById(id);
        return ResponseEntity.noContent().build();
    }
}
