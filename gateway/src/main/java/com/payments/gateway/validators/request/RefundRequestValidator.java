package com.payments.gateway.validators.request;

import com.payments.gateway.exceptions.request.InvalidRefundRequestException;
import com.payments.gateway.model.RefundRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RefundRequestValidator {
    public void validate(RefundRequest refundRequest) {
        if (refundRequest == null) {
            throw new InvalidRefundRequestException("request cannot be null");
        }
        if (!StringUtils.hasLength(refundRequest.getPaymentId())) {
            throw new InvalidRefundRequestException("paymentId cannot be empty");
        }
        if (refundRequest.getAmount() <= 0) {
            throw new InvalidRefundRequestException("amount must be greater than zero");
        }
    }
}
