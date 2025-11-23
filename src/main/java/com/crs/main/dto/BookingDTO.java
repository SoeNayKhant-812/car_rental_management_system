package com.crs.main.dto;

import com.crs.main.util.enums.BookingUtils;
import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {
    private long id;

    private long user_id;
    private long vehicle_id;
    private LocalDateTime pickup_datetime;
    private LocalDateTime dropOff_datetime;
    private long pickup_location_id;
    private long dropOff_location_id;
    private double estimated_total_price;
    private double actual_total_price;
    private BookingUtils.BookingStatus booking_status;
    private BookingUtils.BookingType booking_type;
    private String notes;
}
