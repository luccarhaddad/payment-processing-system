package com.consumer.flink.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundEvent {
    private String paymentId;
    private Integer amount;
    private long timestamp;
}
