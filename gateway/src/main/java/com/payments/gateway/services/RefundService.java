package com.payments.gateway.services;

import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.model.RefundRequest;
import com.payments.gateway.model.RefundResponse;
import com.payments.gateway.validators.request.RefundRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefundService {

    private final RefundRequestValidator validator;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String REFUND_TOPIC = "refund-commands";

    public RefundResponse process(RefundRequest refundRequest) {
        validator.validate(refundRequest);

        RefundResponse response = new RefundResponse();
        response.setStatus(PaymentStatus.PENDING);

        kafkaTemplate.send(REFUND_TOPIC, refundRequest);

        return response;
    }
}
