package com.payments.gateway.controllers;

import com.payments.gateway.api.RefundApi;
import com.payments.gateway.model.RefundRequest;
import com.payments.gateway.model.RefundResponse;
import com.payments.gateway.services.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/refund")
public class RefundController implements RefundApi {

    private final RefundService refundService;

    @Override
    @PostMapping
    public ResponseEntity<RefundResponse> refundPayment(@RequestBody RefundRequest refundRequest) {
        var refundResponse = refundService.process(refundRequest);
        return ResponseEntity.ok(refundResponse);
    }
}
