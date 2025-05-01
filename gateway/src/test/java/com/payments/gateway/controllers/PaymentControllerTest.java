package com.payments.gateway.controllers;

import com.payments.gateway.exceptions.handler.GlobalExceptionHandler;
import com.payments.gateway.model.PaymentResponse;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PaymentController.class)
@Import(GlobalExceptionHandler.class)
public class PaymentControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockitoBean private PaymentService paymentService;

    @Test
    void shouldReturnOkWhenPaymentIsSuccessful() throws Exception {
        String requestBody = """
                {
                    "amount": 100,
                    "currency": "USD",
                    "paymentMethod": "credit_card",
                    "customerId": "123456",
                    "merchantReference": "TestMerchant"
                }
                """;

        var response = new PaymentResponse();
        response.setPaymentId("12345");
        response.setStatus(PaymentStatus.PENDING);
        response.setCreatedAt(OffsetDateTime.now());
        when(paymentService.process(any())).thenReturn(response);

        mockMvc.perform(post("/payment")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated());

        verify(paymentService).process(any());
    }

    @Test
    void shouldReturnBadRequestWhenPaymentRequestIsNull() throws Exception {
        mockMvc.perform(post("/payment")
                .contentType("application/json")
                // Empty body
        ).andExpect(status().isBadRequest());

        verify(paymentService, never()).process(any());
    }
}
