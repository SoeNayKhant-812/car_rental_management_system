package com.crs.main.controller;

import com.crs.main.dto.PaymentDTO;
import com.crs.main.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PaymentDTO>> getAllCars() {
        List<PaymentDTO> cars = paymentService.getAllPayments();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getCarById(@PathVariable Long id) {
        PaymentDTO car = paymentService.getPaymentById(id);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> createCar(@RequestBody PaymentDTO carDTO) {
        PaymentDTO createdCar = paymentService.savePayment(carDTO);
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<PaymentDTO> updateCar(@PathVariable Long id,@RequestBody PaymentDTO carDTO) {
        PaymentDTO updatedCar = paymentService.update(id, carDTO);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }
}
