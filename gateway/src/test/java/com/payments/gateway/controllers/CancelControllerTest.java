package com.payments.gateway.controllers;

import com.payments.gateway.exceptions.handler.GlobalExceptionHandler;
import com.payments.gateway.model.CancelRequest;
import com.payments.gateway.model.CancelResponse;
import com.payments.gateway.model.PaymentStatus;
import com.payments.gateway.services.CancelService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CancelController.class)
@Import(GlobalExceptionHandler.class)
public class CancelControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockitoBean private CancelService cancelService;

    @Test
    void shouldReturnCancelResponse() throws Exception{
        String requestBody = """
                { "paymentId": "12345" }
                """;

        var response = new CancelResponse();
        response.setStatus(PaymentStatus.PENDING);
        when(cancelService.process(any(CancelRequest.class))).thenReturn(response);

        mockMvc.perform(post("/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(PaymentStatus.PENDING.toString()));

        verify(cancelService).process(any());
    }

    @Test
    void shouldReturnBadRequestWhenCancelRequestIsNull() throws Exception {
        mockMvc.perform(post("/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                // Empty body
        ).andExpect(status().isBadRequest());

        verify(cancelService, never()).process(any());
    }
}
