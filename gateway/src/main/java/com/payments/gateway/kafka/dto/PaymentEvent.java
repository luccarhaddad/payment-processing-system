package com.payments.gateway.kafka.dto;

import lombok.Builder;

@Builder
public class PaymentEvent {
    private String paymentId;
    private Integer amount;
    private String currency;
    private String paymentMethod;
    private String customerId;
    private long timestamp;
}
