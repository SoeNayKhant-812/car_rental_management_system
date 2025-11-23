package com.crs.main.repository;

import com.crs.main.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
    VehicleType getVehicleTypeByName(String name);
}
