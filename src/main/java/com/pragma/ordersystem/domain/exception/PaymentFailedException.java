package com.pragma.ordersystem.domain.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentFailedException extends RuntimeException {
    private final UUID orderId;
    private final UUID customerId;
    private final BigDecimal amount;
    private final PaymentMethod paymentMethod;
    private final PaymentFailureReason reason;
    private final String transactionId;
    private final long timestamp;

    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        PAYPAL,
        BANK_TRANSFER,
        CRYPTO
    }

    public enum PaymentFailureReason {
        INSUFFICIENT_FUNDS,
        CARD_EXPIRED,
        INVALID_CARD,
        PAYMENT_GATEWAY_ERROR,
        NETWORK_ERROR,
        FRAUD_DETECTED,
        LIMIT_EXCEEDED,
        CURRENCY_NOT_SUPPORTED,
        TIMEOUT,
        UNKNOWN
    }

    public PaymentFailedException(UUID orderId, UUID customerId, BigDecimal amount, PaymentMethod method, PaymentFailureReason reason) {
        super(buildMessage(orderId, amount, method, reason));
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = method;
        this.reason = reason;
        this.transactionId = null;
        this.timestamp = System.currentTimeMillis();
    }

    public PaymentFailedException(UUID orderId, UUID customerId, BigDecimal amount, 
                                  PaymentMethod method, PaymentFailureReason reason, String transactionId) {
        super(buildMessageWithTransaction(orderId, amount, method, reason, transactionId));
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = method;
        this.reason = reason;
        this.transactionId = transactionId;
        this.timestamp = System.currentTimeMillis();
    }

    public PaymentFailedException(String message) {
        super(message);
        this.orderId = null;
        this.customerId = null;
        this.amount = null;
        this.paymentMethod = null;
        this.reason = PaymentFailureReason.UNKNOWN;
        this.transactionId = null;
        this.timestamp = System.currentTimeMillis();
    }

    private static String buildMessage(UUID orderId, BigDecimal amount, PaymentMethod method, PaymentFailureReason reason) {
        return String.format("Payment failed for order %s: Amount=%s, Method=%s, Reason=%s",
                orderId != null ? orderId.toString() : "unknown",
                amount != null ? amount.toString() : "0.00",
                method != null ? method.name() : "UNKNOWN",
                reason != null ? reason.name() : "UNKNOWN");
    }

    private static String buildMessageWithTransaction(UUID orderId, BigDecimal amount, 
                                                       PaymentMethod method, PaymentFailureReason reason, String transactionId) {
        return String.format("Payment failed for order %s: Amount=%s, Method=%s, Reason=%s, TransactionId=%s",
                orderId != null ? orderId.toString() : "unknown",
                amount != null ? amount.toString() : "0.00",
                method != null ? method.name() : "UNKNOWN",
                reason != null ? reason.name() : "UNKNOWN",
                transactionId != null ? transactionId : "N/A");
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentFailureReason getReason() {
        return reason;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return "PAYMENT_FAILED";
    }

    public boolean isRetryable() {
        return reason == PaymentFailureReason.NETWORK_ERROR || 
               reason == PaymentFailureReason.TIMEOUT || 
               reason == PaymentFailureReason.PAYMENT_GATEWAY_ERROR;
    }

    public boolean isFraudRelated() {
        return reason == PaymentFailureReason.FRAUD_DETECTED;
    }

    public String getDetailedMessage() {
        return String.format("ErrorCode: %s, OrderId: %s, CustomerId: %s, Amount: %s, Method: %s, Reason: %s, TransactionId: %s, Timestamp: %d",
                getErrorCode(),
                orderId != null ? orderId.toString() : "null",
                customerId != null ? customerId.toString() : "null",
                amount != null ? amount.toString() : "null",
                paymentMethod != null ? paymentMethod.name() : "null",
                reason != null ? reason.name() : "null",
                transactionId,
                timestamp);
    }
}