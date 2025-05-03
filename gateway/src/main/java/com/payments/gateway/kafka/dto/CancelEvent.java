package com.payments.gateway.kafka.dto;

import lombok.Builder;

@Builder
public class CancelEvent {
    private String paymentId;
    private long timestamp;
}
