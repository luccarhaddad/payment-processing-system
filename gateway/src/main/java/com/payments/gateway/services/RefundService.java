package com.payments.gateway.services;

import com.payments.gateway.kafka.dto.RefundEvent;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.model.RefundRequest;
import com.payments.gateway.model.RefundResponse;
import com.payments.gateway.validators.request.RefundRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRequestValidator validator;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String REFUND_TOPIC = "refund-commands";

    public RefundResponse process(RefundRequest refundRequest) {
        validator.validate(refundRequest);

        RefundResponse response = new RefundResponse();
        var now = Instant.now();
        response.setStatus(PaymentStatus.PENDING);
        response.setProcessedAt(String.valueOf(now));

        var refundEvent = RefundEvent.builder()
                .paymentId(refundRequest.getPaymentId())
                .amount(refundRequest.getAmount())
                .timestamp(now.toEpochMilli())
                .build();

        kafkaTemplate.send(REFUND_TOPIC, refundEvent);

        return response;
    }
}
