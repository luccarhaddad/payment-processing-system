package com.payments.gateway.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets PaymentStatus
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-03T16:01:17.482024-03:00[America/Sao_Paulo]", comments = "Generator version: 7.4.0")
public enum PaymentStatus {
  
  PENDING("PENDING"),
  
  COMPLETED("COMPLETED"),
  
  FAILED("FAILED"),
  
  CANCELLED("CANCELLED"),
  
  REFUNDED("REFUNDED");

  private String value;

  PaymentStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PaymentStatus fromValue(String value) {
    for (PaymentStatus b : PaymentStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

