package com.crs.main.mapper;

import com.crs.main.dto.MaintenanceDTO;
import com.crs.main.model.Maintenance;

public class MaintenanceMapper {
    public static MaintenanceDTO toDTO(Maintenance maintenance) {
        if (maintenance == null) {
            return null;
        }
        MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
        maintenanceDTO.setId(maintenance.getId());
        maintenanceDTO.setVehicleId(maintenance.getVehicleId());
        maintenanceDTO.setService_type(maintenance.getService_type());
        maintenanceDTO.setService_date(maintenance.getService_date());
        maintenanceDTO.setPrice(maintenance.getPrice());
        maintenanceDTO.setService_center(maintenance.getService_center());
        maintenanceDTO.setDescription(maintenance.getDescription());
        maintenanceDTO.setNext_service_due_date(maintenance.getNext_service_due_date());
        return maintenanceDTO;
    }

    public static Maintenance toEntity(MaintenanceDTO dto) {
        if (dto == null) {
            return null;
        }
        Maintenance maintenance = new Maintenance();
        maintenance.setId(dto.getId());
        maintenance.setVehicleId(dto.getVehicleId());
        maintenance.setService_type(dto.getService_type());
        maintenance.setService_date(dto.getService_date());
        maintenance.setPrice(dto.getPrice());
        maintenance.setService_center(dto.getService_center());
        maintenance.setDescription(dto.getDescription());
        maintenance.setNext_service_due_date(dto.getNext_service_due_date());
        return maintenance;
    }
}
