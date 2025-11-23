package com.crs.main.service;

import com.crs.main.dto.VehicleTypeDTO;
import com.crs.main.mapper.VehicleTypeMapper;
import com.crs.main.model.VehicleType;
import com.crs.main.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeService {
    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleTypeService(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    public VehicleTypeDTO saveVehicleType(VehicleTypeDTO vehicleTypeDto) {
        VehicleType vehicleType = VehicleTypeMapper.toEntity(vehicleTypeDto);
        return VehicleTypeMapper.toDTO(vehicleTypeRepository.save(vehicleType));
    }


    public VehicleTypeDTO getVehicleTypeById(Long id) {
        return VehicleTypeMapper.toDTO(vehicleTypeRepository.findById(id).orElse(null));
    }

    public List<VehicleTypeDTO> getVehicleTypes() {
        return vehicleTypeRepository.findAll().stream().map(VehicleTypeMapper::toDTO).toList();
    }

    public VehicleTypeDTO getVehicleTypeByName(String name) {
        return VehicleTypeMapper.toDTO(vehicleTypeRepository.getVehicleTypeByName(name));
    }

    public VehicleTypeDTO update(Long id, VehicleTypeDTO vehicleTypeDto) {
        VehicleType existingVehicleType = vehicleTypeRepository.findById(id).orElse(null);
        VehicleType updatedVehicleType = new VehicleType();
        if (existingVehicleType != null) {
            VehicleType vehicleType = VehicleTypeMapper.toEntity(vehicleTypeDto);
            vehicleType.setId(id);
            updatedVehicleType = vehicleTypeRepository.save(vehicleType);
        }
        return VehicleTypeMapper.toDTO(updatedVehicleType);
    }

    public void deleteVehicleTypeById(Long id) {
        vehicleTypeRepository.deleteById(id);
    }
}
