package com.crs.main.util.enums;

public class PaymentUtils {
    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        CRYPTOCURRENCY,
        BANK_TRANSFER,
        VALUABLE_ASSET,
        CASH
    }

    public enum PaymentStatus {
        PENDING,// payment initiated but not completed
        PROCESSING,// payment is being processed
        COMPLETED,// payment successful
        PARTIALLY_COMPLETED,// partial payment made
        FAILED,// payment failed
        CANCELLED,// payment cancelled by user
        REFUNDED// full refund issued
    }

    public enum PaymentPlan {
        FULL_PAYMENT,// single payment
        DEPOSIT_PLUS_FULL_BALANCE,// full balance after deposit
        DEPOSIT_PLUS_YEARLY_INSTALLMENTS,// above 12 months
        DEPOSIT_PLUS_QUARTERLY_INSTALLMENTS,// 3 to 6 months
        DEPOSIT_PLUS_MONTHLY_INSTALLMENTS,// 1 to 12 months
        DEPOSIT_PLUS_WEEKLY_INSTALLMENTS,// 1 week to 3 months
        DEPOSIT_PLUS_DAILY_INSTALLMENTS,// up to 7 days
        OTHER
    }
}
