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
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }
    public enum PaymentPlan {
        FULL_PAYMENT,
        DEPOSIT_PLUS_FULL_BALANCE,
        DEPOSIT_PLUS_YEARLY_INSTALLMENTS,
        DEPOSIT_PLUS_QUARTERLY_INSTALLMENTS,
        DEPOSIT_PLUS_MONTHLY_INSTALLMENTS,
        DEPOSIT_PLUS_WEEKLY_INSTALLMENTS,
        DEPOSIT_PLUS_DAILY_INSTALLMENTS,
        PREPAID_YEARLY_INSTALLMENT,
        PREPAID_QUARTERLY_INSTALLMENT,
        PREPAID_MONTHLY_INSTALLMENT,
        PREPAID_WEEKLY_INSTALLMENT,
        PREPAID_DAILY_INSTALLMENT
    }
}
