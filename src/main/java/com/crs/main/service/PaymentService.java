package com.crs.main.service;

import com.crs.main.dto.PaymentDTO;
import com.crs.main.mapper.PaymentMapper;
import com.crs.main.model.Payment;
import com.crs.main.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentDTO savePayment(PaymentDTO paymentDto) {
        Payment payment = PaymentMapper.toEntity(paymentDto);
        return PaymentMapper.toDTO(paymentRepository.save(payment));
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(PaymentMapper::toDTO).toList();
    }

    public PaymentDTO getPaymentById(Long id) {
        return PaymentMapper.toDTO(paymentRepository.findById(id).orElse(null));
    }

    public PaymentDTO update(Long id, PaymentDTO paymentDto) {
        Payment existingPayment = paymentRepository.findById(id).orElse(null);
        Payment updatedPayment = new Payment();
        if (existingPayment != null) {
            Payment payment = PaymentMapper.toEntity(paymentDto);
            payment.setId(id);
            updatedPayment = paymentRepository.save(payment);
        }
        return PaymentMapper.toDTO(updatedPayment);
    }

    public void deletePaymentById(Long id) {
        paymentRepository.deleteById(id);
    }
}
