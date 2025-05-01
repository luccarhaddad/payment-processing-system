package com.payments.gateway.exceptions.request;

import com.payments.gateway.exceptions.GatewayException;

public class InvalidRefundRequestException extends GatewayException {
    public InvalidRefundRequestException(String reason) {
        super("Invalid refund request: " + reason);
    }
}
