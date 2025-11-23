package com.crs.main.model;

import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long user_id;
    private long vehicle_id;
    private LocalDateTime pickup_datetime;
    private LocalDateTime dropOff_datetime;
    private long pickup_location_id;
    private long dropOff_location_id;
    private double estimated_total_price;
    private double actual_total_price;

    private RentUtils.RentType rent_type;
    private PaymentUtils.PaymentPlan payment_plan;
    private RentUtils.PaymentStatus payment_status;
    private RentUtils.RentalStatus rental_status;

    private ArrayList<Payment> payments;

    private String notes;
    private Instant created_at;
    private Instant updated_at;
}
