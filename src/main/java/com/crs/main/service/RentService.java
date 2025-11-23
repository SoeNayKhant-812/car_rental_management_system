package com.crs.main.service;

import com.crs.main.dto.RentDTO;
import com.crs.main.mapper.RentMapper;
import com.crs.main.model.Payment;
import com.crs.main.model.Rent;
import com.crs.main.repository.RentRepository;
import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;

    @Autowired
    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public RentDTO save(RentDTO rentDTO) {
        Rent rent = RentMapper.toEntity(rentDTO);

        //implementations

//        private long id;
//        private long user_id;
//        private long vehicle_id;
//        private LocalDateTime pickup_datetime;
//        private LocalDateTime dropOff_datetime;
//        private long pickup_location_id;
//        private long dropOff_location_id;
//        private double estimated_total_price;
//        private double actual_total_price;
//        private RentUtils.RentType rent_type;
//        private PaymentUtils.PaymentPlan payment_plan;
//        private RentUtils.PaymentStatus payment_status;
//        private RentUtils.RentalStatus rental_status;
//        private ArrayList<Payment> payments;
//        private String notes;
//        private Instant created_at;
//        private Instant updated_at;

        rent.setCreated_at(Instant.now());
        rent.setUpdated_at(Instant.now());

        if(rent.getPayment_plan() == PaymentUtils.PaymentPlan.FULL_PAYMENT) {
            ArrayList<Payment> payments = new ArrayList<>();
            Payment payment = new Payment();
            payment.setRent_id(rent.getId());
            payment.setPaymentPlan(PaymentUtils.PaymentPlan.FULL_PAYMENT);
            payment.setPaymentStatus(PaymentUtils.PaymentStatus.PENDING);

            payment.setAmount(0);//calculate full amount <---------------------------------------
            payments.add(payment);
            rent.setPayments(payments);
        }else if(rent.getPayment_plan() == PaymentUtils.PaymentPlan.OTHER){
            ArrayList<Payment> payments = new ArrayList<>();
            Payment payment = new Payment();
            payment.setRent_id(rent.getId());
            payment.setPaymentPlan(PaymentUtils.PaymentPlan.OTHER);
            payment.setPaymentStatus(PaymentUtils.PaymentStatus.PENDING);

            payment.setAmount(0);//calculate other amount <---------------------------------------
            payments.add(payment);
            rent.setPayments(payments);
        }else{
            ArrayList<Payment> payments = new ArrayList<>();

        }

        return RentMapper.toDTO(rentRepository.save(rent));
    }

    public List<RentDTO> findAll() {
        return rentRepository.findAll().stream().map(RentMapper::toDTO).toList();
    }

    public RentDTO findById(long id) {
        return RentMapper.toDTO(rentRepository.findById(id).orElse(null));
    }

    public RentDTO update(Long id, RentDTO rentDto) {
        Rent existingRent = rentRepository.findById(id).orElse(null);
        Rent updatedRent = new Rent();
        if (existingRent != null) {
            Rent rent = RentMapper.toEntity(rentDto);
            rent.setId(id);
            updatedRent = rentRepository.save(rent);
        }
        return RentMapper.toDTO(updatedRent);
    }

    public void deleteById(long id) {
        rentRepository.deleteById(id);
    }
}
