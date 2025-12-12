package com.crs.main.model;

import com.crs.main.util.enums.PaymentUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long rentId;
    private PaymentUtils.PaymentMethod paymentMethod;  // enum used
    private PaymentUtils.PaymentStatus paymentStatus;  // enum used
    private PaymentUtils.PaymentPlan paymentPlan; // enum used
    private String transactionId;
    private BigDecimal amount;
    private Instant paymentDate;
}
