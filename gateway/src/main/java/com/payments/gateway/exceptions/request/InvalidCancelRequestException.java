package com.payments.gateway.exceptions.request;

import com.payments.gateway.exceptions.GatewayException;

public class InvalidCancelRequestException extends GatewayException {
    public InvalidCancelRequestException(String reason) {
        super("Invalid cancel request: " + reason);
    }
}
