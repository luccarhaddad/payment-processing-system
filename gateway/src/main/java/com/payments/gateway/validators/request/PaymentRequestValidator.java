package com.payments.gateway.validators.request;

import com.payments.gateway.exceptions.request.InvalidPaymentRequestException;
import com.payments.gateway.model.PaymentRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PaymentRequestValidator {
    public void validate(PaymentRequest paymentRequest) {
        if (paymentRequest == null) {
            throw new InvalidPaymentRequestException("request cannot be null");
        }
        if (paymentRequest.getAmount() <= 0) {
            throw new InvalidPaymentRequestException("amount must be greater than zero");
        }
        if (!StringUtils.hasLength(paymentRequest.getCurrency())) {
            throw new InvalidPaymentRequestException("currency cannot be empty");
        }
        if (!StringUtils.hasLength(paymentRequest.getPaymentMethod())) {
            throw new InvalidPaymentRequestException("method cannot be empty");
        }
        if (!StringUtils.hasLength(paymentRequest.getCustomerId())) {
            throw new InvalidPaymentRequestException("customerId cannot be empty");
        }
    }
}
