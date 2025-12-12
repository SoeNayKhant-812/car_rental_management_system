package com.crs.main.service;

import com.crs.main.dto.PaymentDTO;
import com.crs.main.mapper.PaymentMapper;
import com.crs.main.model.Payment;
import com.crs.main.model.Rent;
import com.crs.main.repository.PaymentRepository;
import com.crs.main.repository.RentRepository;
import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final RentRepository rentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, RentRepository rentRepository) {
        this.rentRepository = rentRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentDTO processPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (payment.getPaymentStatus() == PaymentUtils.PaymentStatus.COMPLETED) {
            throw new RuntimeException("Payment is already completed.");
        }

        payment.setPaymentStatus(PaymentUtils.PaymentStatus.COMPLETED);
        payment.setPaymentDate(Instant.now());
        Payment savedPayment = paymentRepository.save(payment);

        updateRentStatusAfterPayment(payment.getRentId());

        return PaymentMapper.toDTO(savedPayment);
    }

    private void updateRentStatusAfterPayment(Long rentId) {
        Rent rent = rentRepository.findById(rentId).orElseThrow();
        List<Payment> allPayments = paymentRepository.findByRentId(rentId);

        long totalPayments = allPayments.size();
        long completedPayments = allPayments.stream()
                .filter(p -> p.getPaymentStatus() == PaymentUtils.PaymentStatus.COMPLETED)
                .count();

        boolean isFullPaymentPlan = rent.getPayment_plan() == PaymentUtils.PaymentPlan.FULL_PAYMENT;

        if (totalPayments == completedPayments) {
            rent.setPayment_status(RentUtils.PaymentStatus.FULLY_PAID);
            rent.setRental_status(RentUtils.RentalStatus.RENTED);
        } else if (completedPayments > 0) {
            if (isFullPaymentPlan) {
                rent.setPayment_status(RentUtils.PaymentStatus.FULLY_PAID);
            } else {
                if (completedPayments == 1) {
                    rent.setPayment_status(RentUtils.PaymentStatus.DEPOSIT_PAID);
                    rent.setRental_status(RentUtils.RentalStatus.PENDING);
                } else {
                    rent.setPayment_status(RentUtils.PaymentStatus.PAYING_WITH_INSTALLMENTS);
                    rent.setRental_status(RentUtils.RentalStatus.RENTED);
                }
            }
        } else {
            rent.setPayment_status(RentUtils.PaymentStatus.UNPAID);
        }
        rentRepository.save(rent);
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

    public List<PaymentDTO> getPaymentByRentId(Long rentId) {
        return paymentRepository.findByRentId(rentId).stream().map(PaymentMapper::toDTO).toList();
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
