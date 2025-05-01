package com.payments.gateway.validators.request;

import com.payments.gateway.exceptions.request.InvalidCancelRequestException;
import com.payments.gateway.model.CancelRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CancelRequestValidator {
    public void validate(CancelRequest cancelRequest) {
        if (cancelRequest == null) {
            throw new InvalidCancelRequestException("request cannot be null");
        }
        if (!StringUtils.hasLength(cancelRequest.getPaymentId())) {
            throw new InvalidCancelRequestException("paymentId cannot be empty");
        }
    }
}
