package com.payments.gateway.controllers;

import com.payments.gateway.api.PaymentApi;
import com.payments.gateway.model.PaymentRequest;
import com.payments.gateway.model.PaymentResponse;
import com.payments.gateway.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController implements PaymentApi {

    private final PaymentService paymentService;

    @Override
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest) {
        var paymentResponse = paymentService.process(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }
}
