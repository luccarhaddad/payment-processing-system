package com.payments.gateway.services;

import com.payments.gateway.model.PaymentRequest;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.validators.request.PaymentRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock private PaymentRequestValidator validator;
    @Mock private KafkaTemplate<String, Object> kafkaTemplate;
    @InjectMocks private PaymentService paymentService;

    @Test
    void shouldProcessPayment() {
        // Arrange
        var paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(100);
        paymentRequest.setCurrency("USD");
        paymentRequest.setPaymentMethod("credit_card");
        paymentRequest.setCustomerId("123456");
        paymentRequest.setMerchantReference("TestMerchant");

        // Act
        var response = paymentService.process(paymentRequest);

        // Assert
        assertNotNull(response);
        assertEquals(PaymentStatus.PENDING, response.getStatus());
    }

    @Test
    void processDoesNotSendMessageWhenKafkaTemplateFails() {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100);
        request.setCurrency("USD");
        request.setPaymentMethod("credit_card");
        request.setCustomerId("123456");
        request.setMerchantReference("TestMerchant");

        doNothing().when(validator).validate(request);
        doThrow(new RuntimeException("Kafka error")).when(kafkaTemplate).send("payment-commands", request);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> paymentService.process(request));
        verify(validator).validate(request);
        verify(kafkaTemplate).send("payment-commands", request);
    }
}
