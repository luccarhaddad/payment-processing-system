package com.payments.gateway.services;

import com.payments.gateway.model.CancelRequest;
import com.payments.gateway.model.CancelResponse;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.validators.request.CancelRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelService {

    private final CancelRequestValidator validator;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String CANCEL_TOPIC = "cancel-commands";

    public CancelResponse process(CancelRequest cancelRequest) {
        validator.validate(cancelRequest);

        CancelResponse response = new CancelResponse();
        response.setStatus(PaymentStatus.PENDING);

        kafkaTemplate.send(CANCEL_TOPIC, cancelRequest);

        return response;
    }
}