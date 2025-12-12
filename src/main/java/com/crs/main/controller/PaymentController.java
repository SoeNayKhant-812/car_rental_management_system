package com.crs.main.controller;

import com.crs.main.dto.PaymentDTO;
import com.crs.main.mapper.PaymentMapper;
import com.crs.main.model.Payment;
import com.crs.main.model.Rent;
import com.crs.main.repository.PaymentRepository;
import com.crs.main.repository.RentRepository;
import com.crs.main.service.PaymentService;
import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> cars = paymentService.getAllPayments();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        PaymentDTO car = paymentService.getPaymentById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/rent/{rentId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentByRentId(@PathVariable Long rentId) {
        List<PaymentDTO> payments = paymentService.getPaymentByRentId(rentId);
        return ResponseEntity.ok(payments);
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO carDTO) {
        PaymentDTO createdCar = paymentService.savePayment(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @RequestBody PaymentDTO carDTO) {
        PaymentDTO updatedCar = paymentService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }
}