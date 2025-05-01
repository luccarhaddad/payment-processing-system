package com.payments.gateway.services;

import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.model.RefundRequest;
import com.payments.gateway.model.RefundResponse;
import com.payments.gateway.validators.request.RefundRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RefundServiceTest {

    @Mock private KafkaTemplate<String, Object> kafkaTemplate;
    @Mock private RefundRequestValidator validator;
    @InjectMocks private RefundService refundService;

    @Test
    void processReturnsPendingStatusWhenRequestIsValid() {
        // Arrange
        RefundRequest request = new RefundRequest();
        request.setPaymentId("12345");
        request.setAmount(100);

        // Act
        RefundResponse response = refundService.process(request);

        // Assert
        assertEquals(PaymentStatus.PENDING, response.getStatus());
        verify(validator).validate(request);
        verify(kafkaTemplate).send("refund-commands", request);
    }

    @Test
    void processDoesNotSendMessageWhenKafkaTemplateFails() {
        // Arrange
        RefundRequest request = new RefundRequest();
        request.setPaymentId("12345");
        request.setAmount(100);

        doNothing().when(validator).validate(request);
        doThrow(new RuntimeException("Kafka error")).when(kafkaTemplate).send("cancel-commands", request);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> refundService.process(request));
        verify(validator).validate(request);
        verify(kafkaTemplate).send("refund-commands", request);
    }
}
