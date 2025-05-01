package com.payments.gateway.validators;

import com.payments.gateway.exceptions.request.InvalidRefundRequestException;
import com.payments.gateway.model.RefundRequest;
import com.payments.gateway.validators.request.RefundRequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RefundRequestValidatorTest {

    private final RefundRequestValidator refundRequestValidator = new RefundRequestValidator();

    @Test
    public void shouldNotThrowExceptionWhenRequestIsValid() {
        // Arrange
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setPaymentId("12345");
        refundRequest.setAmount(100);

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> refundRequestValidator.validate(refundRequest));
    }

    @Test
    public void shouldThrowExceptionWhenRequestIsNull() {
        // Act & Assert
        assertThrows(InvalidRefundRequestException.class, () -> refundRequestValidator.validate(null));
    }

    @Test
    public void shouldThrowExceptionWhenPaymentIdIsEmpty() {
        // Arrange
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setPaymentId("");
        refundRequest.setAmount(100);

        // Act & Assert
        assertThrows(InvalidRefundRequestException.class, () -> refundRequestValidator.validate(refundRequest));
    }

    @Test
    public void shouldThrowExceptionWhenAmountIsLessThanOrEqualToZero() {
        // Arrange
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setPaymentId("12345");
        refundRequest.setAmount(0);

        // Act & Assert
        assertThrows(InvalidRefundRequestException.class, () -> refundRequestValidator.validate(refundRequest));
    }
}
