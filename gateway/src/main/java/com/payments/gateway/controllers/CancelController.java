package com.payments.gateway.controllers;

import com.payments.gateway.api.CancelApi;
import com.payments.gateway.model.CancelRequest;
import com.payments.gateway.model.CancelResponse;
import com.payments.gateway.services.CancelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cancel")
public class CancelController implements CancelApi {

    private final CancelService cancelService;

    @Override
    @PostMapping
    public ResponseEntity<CancelResponse> cancelPayment(@RequestBody CancelRequest cancelRequest) {
        var cancelResponse = cancelService.process(cancelRequest);
        return ResponseEntity.ok(cancelResponse);
    }
}
