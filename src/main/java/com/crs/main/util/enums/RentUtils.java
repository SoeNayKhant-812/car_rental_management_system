package com.crs.main.util.enums;

public class RentUtils {
    public enum RentalStatus {
        BOOKED, // Booking confirmed but car not yet picked up
        ONGOING, // Customer currently has the car
        RETURNED, // Car has been returned
        OVERDUE, // Car not returned by due date
        CANCELLED // Booking was cancelled
    }
    public enum RentType {
        SHORT_TERM,
        MID_TERM,
        LONG_TERM
    }
    public enum PaymentStatus {
        PENDING,
        UNPAID,
        COMPLETED,
        PAYING_WITH_INSTALLMENTS,
        DEPOSIT_PAID,
        FAILED,
        CANCELLED,
        REFUNDED,
        PARTIALLY_REFUNDED
    }}
