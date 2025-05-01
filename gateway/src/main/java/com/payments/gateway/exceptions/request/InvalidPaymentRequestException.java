package com.payments.gateway.exceptions.request;

import com.payments.gateway.exceptions.GatewayException;

public class InvalidPaymentRequestException extends GatewayException {
    public InvalidPaymentRequestException(String reason) {
        super("Invalid payment request: " + reason);
    }
}
