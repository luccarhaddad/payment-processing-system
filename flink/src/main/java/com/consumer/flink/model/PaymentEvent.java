package com.consumer.flink.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {

    @JsonProperty("paymentId")
    private String paymentId;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("paymentMethod")
    private String paymentMethod;

    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("merchantReference")
    private String merchantReference;

    @JsonProperty("timestamp")
    private long timestamp;
}
