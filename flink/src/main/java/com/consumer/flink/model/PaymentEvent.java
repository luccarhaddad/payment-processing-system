package com.consumer.flink.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private String paymentId;
    private Integer amount;
    private String currency;
    private String paymentMethod;
    private String customerId;
    private long timestamp;
}
