package com.payments.gateway.validators;

import com.payments.gateway.exceptions.request.InvalidCancelRequestException;
import com.payments.gateway.model.CancelRequest;
import com.payments.gateway.validators.request.CancelRequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CancelRequestValidatorTest {

    private final CancelRequestValidator cancelRequestValidator = new CancelRequestValidator();

    @Test
    public void shouldNotThrowExceptionWhenRequestIsValid() {
        // Arrange
        CancelRequest cancelRequest = new CancelRequest();
        cancelRequest.setPaymentId("12345");

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> cancelRequestValidator.validate(cancelRequest));
    }

    @Test
    public void shouldThrowExceptionWhenRequestIsNull() {
        // Act & Assert
        Assertions.assertThrows(InvalidCancelRequestException.class, () -> cancelRequestValidator.validate(null));
    }

    @Test
    public void shouldThrowExceptionWhenPaymentIdIsEmpty() {
        // Arrange
        CancelRequest cancelRequest = new CancelRequest();
        cancelRequest.setPaymentId("");

        // Act & Assert
        Assertions.assertThrows(InvalidCancelRequestException.class, () -> cancelRequestValidator.validate(cancelRequest));
    }
}
