package com.payments.gateway.services;

import com.payments.gateway.kafka.dto.CancelEvent;
import com.payments.gateway.model.CancelRequest;
import com.payments.gateway.model.CancelResponse;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.validators.request.CancelRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CancelService {

    private final CancelRequestValidator validator;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String CANCEL_TOPIC = "cancel-commands";

    public CancelResponse process(CancelRequest cancelRequest) {
        validator.validate(cancelRequest);

        CancelResponse response = new CancelResponse();
        var now = Instant.now();
        response.setStatus(PaymentStatus.PENDING);
        response.setProcessedAt(String.valueOf(now));

        var cancelEvent = CancelEvent.builder()
                .paymentId(cancelRequest.getPaymentId())
                .timestamp(now.toEpochMilli())
                .build();

        kafkaTemplate.send(CANCEL_TOPIC, cancelEvent);

        return response;
    }
}