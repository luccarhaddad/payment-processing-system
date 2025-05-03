package com.consumer.flink.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelEvent {
    @JsonProperty("paymentId")
    private String paymentId;

    @JsonProperty("timestamp")
    private long timestamp;
}
