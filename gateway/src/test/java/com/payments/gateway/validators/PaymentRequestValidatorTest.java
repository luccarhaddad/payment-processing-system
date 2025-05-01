package com.payments.gateway.validators;

import com.payments.gateway.exceptions.request.InvalidPaymentRequestException;
import com.payments.gateway.model.PaymentRequest;
import com.payments.gateway.validators.request.PaymentRequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentRequestValidatorTest {

    private final PaymentRequestValidator paymentRequestValidator = new PaymentRequestValidator();

    @Test
    public void shouldNotThrowExceptionWhenRequestIsValid() {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(100);
        paymentRequest.setCurrency("USD");
        paymentRequest.setPaymentMethod("CREDIT_CARD");
        paymentRequest.setCustomerId("1234");
        paymentRequest.setMerchantReference("TestMerchant");

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> paymentRequestValidator.validate(paymentRequest));
    }

    @Test
    public void shouldThrowExceptionWhenRequestIsNull() {
        // Act & Assert
        Assertions.assertThrows(InvalidPaymentRequestException.class, () -> paymentRequestValidator.validate(null));
    }

    @Test
    public void shouldThrowExceptionWhenAmountIsLessThanOrEqualToZero() {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(0);
        paymentRequest.setCurrency("USD");
        paymentRequest.setPaymentMethod("CREDIT_CARD");
        paymentRequest.setCustomerId("1234");
        paymentRequest.setMerchantReference("TestMerchant");

        // Act & Assert
        Assertions.assertThrows(InvalidPaymentRequestException.class, () -> paymentRequestValidator.validate(paymentRequest));
    }

    @Test
    public void shouldThrowExceptionWhenCurrencyIsEmpty() {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(100);
        paymentRequest.setCurrency("");
        paymentRequest.setPaymentMethod("CREDIT_CARD");
        paymentRequest.setCustomerId("1234");
        paymentRequest.setMerchantReference("TestMerchant");

        // Act & Assert
        Assertions.assertThrows(InvalidPaymentRequestException.class, () -> paymentRequestValidator.validate(paymentRequest));
    }

    @Test
    public void shouldThrowExceptionWhenPaymentMethodIsEmpty() {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(100);
        paymentRequest.setCurrency("USD");
        paymentRequest.setPaymentMethod("");
        paymentRequest.setCustomerId("1234");
        paymentRequest.setMerchantReference("TestMerchant");

        // Act & Assert
        Assertions.assertThrows(InvalidPaymentRequestException.class, () -> paymentRequestValidator.validate(paymentRequest));
    }

    @Test
    public void shouldThrowExceptionWhenCustomerIdIsEmpty() {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(100);
        paymentRequest.setCurrency("USD");
        paymentRequest.setPaymentMethod("CREDIT_CARD");
        paymentRequest.setCustomerId("");
        paymentRequest.setMerchantReference("TestMerchant");

        // Act & Assert
        Assertions.assertThrows(InvalidPaymentRequestException.class, () -> paymentRequestValidator.validate(paymentRequest));
    }
}
