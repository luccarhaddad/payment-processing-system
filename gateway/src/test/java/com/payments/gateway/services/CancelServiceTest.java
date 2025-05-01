package com.payments.gateway.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.payments.gateway.model.CancelRequest;
import com.payments.gateway.model.CancelResponse;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.validators.request.CancelRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
public class CancelServiceTest {

    @Mock private KafkaTemplate<String, Object> kafkaTemplate;
    @Mock private CancelRequestValidator validator;
    @InjectMocks private CancelService cancelService;

    @Test
    void processReturnsPendingStatusWhenRequestIsValid() {
        // Arrange
        CancelRequest request = new CancelRequest();
        request.setPaymentId("12345");

        // Act
        CancelResponse response = cancelService.process(request);

        // Assert
        assertEquals(PaymentStatus.PENDING, response.getStatus());
        verify(validator).validate(request);
        verify(kafkaTemplate).send("cancel-commands", request);
    }

    @Test
    void processDoesNotSendMessageWhenKafkaTemplateFails() {
        // Arrange
        CancelRequest request = new CancelRequest();
        request.setPaymentId("12345");

        doNothing().when(validator).validate(request);
        doThrow(new RuntimeException("Kafka error")).when(kafkaTemplate).send("cancel-commands", request);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> cancelService.process(request));
        verify(validator).validate(request);
        verify(kafkaTemplate).send("cancel-commands", request);
    }
}