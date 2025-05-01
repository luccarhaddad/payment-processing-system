package com.payments.gateway.services;

import com.payments.gateway.model.PaymentRequest;
import com.payments.gateway.model.PaymentResponse;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.validators.request.PaymentRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRequestValidator validator;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String PAYMENT_TOPIC = "payment-commands";

    public PaymentResponse process(PaymentRequest paymentRequest) {
        validator.validate(paymentRequest);

        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(UUID.randomUUID().toString());
        response.setStatus(PaymentStatus.PENDING);
        response.setCreatedAt(OffsetDateTime.now());

        kafkaTemplate.send(PAYMENT_TOPIC, paymentRequest);

        return response;
    }
}
