package com.crs.main.service;

import com.crs.main.dto.MaintenanceDTO;
import com.crs.main.mapper.MaintenanceMapper;
import com.crs.main.model.Maintenance;
import com.crs.main.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Autowired
    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public MaintenanceDTO saveMaintenance(MaintenanceDTO maintenanceDto) {
        Maintenance maintenance = MaintenanceMapper.toEntity(maintenanceDto);
        return MaintenanceMapper.toDTO(maintenanceRepository.save(maintenance));
    }

    public List<MaintenanceDTO> getAllMaintenances() {
        return maintenanceRepository.findAll().stream().map(MaintenanceMapper::toDTO).toList();
    }

    public MaintenanceDTO getMaintenanceById(Long id) {
        return MaintenanceMapper.toDTO(maintenanceRepository.findById(id).orElse(null));
    }

    public MaintenanceDTO update(Long id, MaintenanceDTO maintenanceDto) {
        Maintenance existingMaintenance = maintenanceRepository.findById(id).orElse(null);
        Maintenance updatedMaintenance = new Maintenance();
        if (existingMaintenance != null) {
            Maintenance maintenance = MaintenanceMapper.toEntity(maintenanceDto);
            maintenance.setId(id);
            updatedMaintenance = maintenanceRepository.save(maintenance);
        }
        return MaintenanceMapper.toDTO(updatedMaintenance);
    }

    public void deleteMaintenanceById(Long id) {
        maintenanceRepository.deleteById(id);
    }
}
