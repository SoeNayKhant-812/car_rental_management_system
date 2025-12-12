package com.crs.main.service;

import com.crs.main.dto.RentDTO;
import com.crs.main.mapper.RentMapper;
import com.crs.main.model.Car;
import com.crs.main.model.Payment;
import com.crs.main.model.Rent;
import com.crs.main.repository.CarRepository;
import com.crs.main.repository.PaymentRepository;
import com.crs.main.repository.RentRepository;
import com.crs.main.util.enums.PaymentUtils;
import com.crs.main.util.enums.RentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final CarRepository carRepository;
    private final PaymentRepository paymentRepository;

    // Constants
    private static final BigDecimal DEPOSIT_PERCENTAGE = BigDecimal.valueOf(0.20); // 20%
    private static final BigDecimal BALANCE_PERCENTAGE = BigDecimal.valueOf(0.80); // 80%

    @Autowired
    public RentService(RentRepository rentRepository, CarRepository carRepository, PaymentRepository paymentRepository) {
        this.carRepository = carRepository;
        this.rentRepository = rentRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public RentDTO save(RentDTO rentDTO) {
        Rent rent = RentMapper.toEntity(rentDTO);

        // 1. Calculate Price
        Car car = carRepository.findById(rent.getVehicle_id())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        long days = calculateRentalDays(rent.getPickup_datetime(), rent.getDropOff_datetime()); // Assuming CamelCase

        // Calculate Total Price (Use BigDecimal from Car entity)
        BigDecimal dailyRate = car.getDaily_rental_rate();
        BigDecimal totalPrice = dailyRate.multiply(BigDecimal.valueOf(days));
        rent.setEstimated_total_price(totalPrice);

        // 2. Set Initial Statuses using your Enums
        rent.setPayment_status(RentUtils.PaymentStatus.UNPAID);
        rent.setRental_status(RentUtils.RentalStatus.PENDING);
        rent.setCreated_at(Instant.now());
        rent.setUpdated_at(Instant.now());

        // 3. Save Rent FIRST to generate ID
        Rent savedRent = rentRepository.save(rent);

        // 4. Generate Payments based on PaymentPlan
        List<Payment> payments = generatePaymentSchedule(savedRent, days);
        paymentRepository.saveAll(payments);

        savedRent.setPayments(new ArrayList<>(payments));
        return RentMapper.toDTO(savedRent);
    }

    private List<Payment> generatePaymentSchedule(Rent rent, long days) {
        List<Payment> payments = new ArrayList<>();
        BigDecimal totalPrice = rent.getEstimated_total_price();
        PaymentUtils.PaymentPlan plan = rent.getPayment_plan();

        if (plan == PaymentUtils.PaymentPlan.FULL_PAYMENT || plan == PaymentUtils.PaymentPlan.OTHER) {
            // Case 1: Single Payment
            payments.add(createPaymentRecord(rent.getId(), totalPrice, plan));
        } else {
            // Case 2: Deposit + Installments

            // A. Create Deposit (20%)
            BigDecimal depositAmount = totalPrice.multiply(DEPOSIT_PERCENTAGE);
            payments.add(createPaymentRecord(rent.getId(), depositAmount, plan));

            // B. Create Installments (80%)
            BigDecimal remainingBalance = totalPrice.multiply(BALANCE_PERCENTAGE);
            payments.addAll(calculateInstallments(rent, days, remainingBalance));
        }
        return payments;
    }

    private List<Payment> calculateInstallments(Rent rent, long days, BigDecimal balance) {
        List<Payment> installments = new ArrayList<>();
        long divisor=0;

        // Determine number of installments based on your Enums
        switch (rent.getPayment_plan()) {
            case DEPOSIT_PLUS_DAILY_INSTALLMENTS -> divisor = days;
            case DEPOSIT_PLUS_WEEKLY_INSTALLMENTS -> divisor = (long) Math.ceil((double) days / 7);
            case DEPOSIT_PLUS_MONTHLY_INSTALLMENTS -> divisor = (long) Math.ceil((double) days / 30);
            case DEPOSIT_PLUS_QUARTERLY_INSTALLMENTS -> divisor = (long) Math.ceil((double) days / 90);
            case DEPOSIT_PLUS_YEARLY_INSTALLMENTS -> divisor = (long) Math.ceil((double) days / 365);
            default -> divisor = 1;
        }

        if (divisor < 1) divisor = 1; // Safety check

        // Divide balance by number of installments (rounding properly)
        BigDecimal amountPerInstallment = balance.divide(BigDecimal.valueOf(divisor), 2, RoundingMode.HALF_UP);

        for (int i = 0; i < divisor; i++) {
            installments.add(createPaymentRecord(rent.getId(), amountPerInstallment, rent.getPayment_plan()));
        }

        return installments;
    }

    private Payment createPaymentRecord(Long rentId, BigDecimal amount, PaymentUtils.PaymentPlan plan) {
        Payment payment = new Payment();
        payment.setRentId(rentId);
        payment.setAmount(amount);
        payment.setPaymentPlan(plan);
        payment.setPaymentStatus(PaymentUtils.PaymentStatus.PENDING);
        return payment;
    }

    private long calculateRentalDays(LocalDateTime pickup, LocalDateTime dropOff) {
        if (pickup == null || dropOff == null) return 0;
        Duration duration = Duration.between(pickup, dropOff);
        long days = duration.toDays();
        if (duration.toHours() % 24 > 0) days++;
        return days == 0 ? 1 : days;
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
