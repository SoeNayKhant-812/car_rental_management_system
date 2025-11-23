package com.crs.main.util.enums;

public class RentUtils {
    public enum RentalStatus {
        PENDING, // Rent status is pending due to no payment
        RENTED, // Car has been rented out
        PICKED_UP, // Car has been picked up by the user
        RETURNED, // Car has been returned
        OVERDUE, // Car not returned by due date
        CANCELLED // Booking was cancelled
    }

    public enum RentType {
        SINGLE_DAY, // up to 24 hours
        SEVEN_DAYS, // 2 to 7 days
        ONE_MONTH, // 8 to 30 days
        SHORT_TERM, // up to 3 months
        QUARTER, // 3 to 6 months
        MID_TERM, // 6 to 12 months
        LONG_TERM, // more than 1 year
        LONG_TERM_SUBSCRIPTION // monthly or yearly subscription
    }

    public enum PaymentStatus {
        UNPAID, // No payment made yet
        PAYMENT_PROCESSED, // Payment is being processed
        FULLY_PAID, // Full payment made
        PAYING_WITH_INSTALLMENTS, // Payment is being made in installments
        DEPOSIT_PAID, // Deposit has been paid
        FAILED, // Payment failed
        CANCELLED, // Payment process was cancelled
        REFUNDED, // Full refund issued
        PARTIALLY_REFUNDED // Partial refund issued
    }
}
