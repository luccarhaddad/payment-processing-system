package com.payments.gateway.kafka.dto;

import lombok.Builder;

@Builder
public class RefundEvent {
    private String paymentId;
    private Integer amount;
    private long timestamp;
}
