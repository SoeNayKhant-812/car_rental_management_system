package com.crs.main.dto;

import com.crs.main.util.enums.PaymentUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class PaymentDTO {
    private long id;

    private long bookingId;
    private PaymentUtils.PaymentMethod paymentMethod;  // enum used
    private PaymentUtils.PaymentStatus paymentStatus; // enum used
    private PaymentUtils.PaymentPlan paymentPlan; // enum used
    private String transactionId;
    private BigDecimal amount;
    private Instant paymentDate;
}
