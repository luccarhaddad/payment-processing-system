package com.payments.gateway.controllers;

import com.payments.gateway.exceptions.handler.GlobalExceptionHandler;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.model.RefundRequest;
import com.payments.gateway.model.RefundResponse;
import com.payments.gateway.services.RefundService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RefundController.class)
@Import(GlobalExceptionHandler.class)
public class RefundControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockitoBean private RefundService refundService;

    @Test
    void shouldReturnRefundResponse() throws Exception {
        String requestBody = """
                { "paymentId": "12345",
                  "amount": 100
                }
                """;

        var response = new RefundResponse();
        response.setStatus(PaymentStatus.PENDING);
        when(refundService.process(any(RefundRequest.class))).thenReturn(response);

        mockMvc.perform(post("/refund")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andExpect(status().isOk());

         verify(refundService).process(any());
    }

    @Test
    void shouldReturnBadRequestWhenRefundRequestIsNull() throws Exception {
        mockMvc.perform(post("/refund")
                .contentType(MediaType.APPLICATION_JSON)
                // Empty body
        ).andExpect(status().isBadRequest());

        verify(refundService, never()).process(any());
    }
}
