package com.payments.gateway.exceptions.handler;

import com.payments.gateway.exceptions.request.InvalidCancelRequestException;
import com.payments.gateway.exceptions.request.InvalidPaymentRequestException;
import com.payments.gateway.exceptions.request.InvalidRefundRequestException;
import com.payments.gateway.model.ErrorCode;
import com.payments.gateway.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadJson(HttpMessageNotReadableException ex) {
        // This covers missing or malformed JSON bodies
        ErrorResponse body = new ErrorResponse(
                ErrorCode.INVALID_REQUEST,
                "Request payload is invalid or missing"
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(InvalidPaymentRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(@NonNull InvalidPaymentRequestException ex) {
        ErrorResponse body = new ErrorResponse(ErrorCode.INVALID_REQUEST, ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(InvalidCancelRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(@NonNull InvalidCancelRequestException ex) {
        ErrorResponse body = new ErrorResponse(ErrorCode.INVALID_REQUEST, ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(InvalidRefundRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequest(@NonNull InvalidRefundRequestException ex) {
        ErrorResponse body = new ErrorResponse(ErrorCode.INVALID_REQUEST, ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric() {
        ErrorResponse body = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        return ResponseEntity.internalServerError().body(body);
    }
}
