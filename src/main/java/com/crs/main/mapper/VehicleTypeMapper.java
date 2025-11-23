package com.crs.main.mapper;

import com.crs.main.dto.VehicleTypeDTO;
import com.crs.main.model.VehicleType;

public class VehicleTypeMapper {
    public static VehicleTypeDTO toDTO(VehicleType vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        VehicleTypeDTO vehicleTypeDTO = new VehicleTypeDTO();
        vehicleTypeDTO.setId(vehicleType.getId());
        vehicleTypeDTO.setName(vehicleType.getName());
        vehicleTypeDTO.setDescription(vehicleType.getDescription());
        vehicleTypeDTO.setFeatures(vehicleType.getFeatures());
        return vehicleTypeDTO;
    }

    public static VehicleType toEntity(VehicleTypeDTO dto) {
        if (dto == null) {
            return null;
        }
        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(dto.getId());
        vehicleType.setName(dto.getName());
        vehicleType.setDescription(dto.getDescription());
        vehicleType.setFeatures(dto.getFeatures());
        return vehicleType;
    }
}
