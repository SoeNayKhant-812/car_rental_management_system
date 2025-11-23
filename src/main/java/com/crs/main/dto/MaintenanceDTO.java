package com.crs.main.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MaintenanceDTO {
    private long id;

    private long vehicleId;
    private String service_type;
    private LocalDate service_date;
    private double price;
    private String service_center;
    private String description;
    private LocalDate next_service_due_date;
}
