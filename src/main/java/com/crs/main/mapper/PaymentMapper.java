package com.crs.main.mapper;

import com.crs.main.dto.PaymentDTO;
import com.crs.main.model.Payment;

public class PaymentMapper {
    public static PaymentDTO toDTO(Payment payment) {
        if (payment == null) {
            return null;
        }
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setRentId(payment.getRentId());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setPaymentPlan(payment.getPaymentPlan());
        dto.setTransactionId(payment.getTransactionId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }

    public static Payment toEntity(PaymentDTO dto) {
        if (dto == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setRentId(dto.getRentId());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setPaymentPlan(dto.getPaymentPlan());
        payment.setTransactionId(dto.getTransactionId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        return payment;
    }
}
