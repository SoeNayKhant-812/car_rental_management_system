package com.crs.main.dto;

import com.crs.main.model.Payment;
import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
public class RentDTO {
    private long id;

    private long user_id;
    private long vehicle_id;
    private LocalDateTime pickup_datetime;
    private LocalDateTime dropOff_datetime;
    private long pickup_location_id;
    private long dropOff_location_id;
    private BigDecimal estimated_total_price;
    private BigDecimal actual_total_price;
    private RentUtils.RentType rent_type; // enum used
    private PaymentUtils.PaymentPlan payment_plan; // enum used
    private RentUtils.PaymentStatus payment_status;  // enum used
    private RentUtils.RentalStatus rental_status; // enum used
    private ArrayList<Payment> payments;
    private String notes;
    private Instant created_at;
    private Instant updated_at;
}
