package com.payments.gateway.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.payments.gateway.model.PaymentStatus;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PaymentResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-03T16:01:17.482024-03:00[America/Sao_Paulo]", comments = "Generator version: 7.4.0")
public class PaymentResponse {

  private String paymentId;

  private PaymentStatus status;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  public PaymentResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentResponse(String paymentId, PaymentStatus status, OffsetDateTime createdAt) {
    this.paymentId = paymentId;
    this.status = status;
    this.createdAt = createdAt;
  }

  public PaymentResponse paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  /**
   * Unique identifier for the transaction
   * @return paymentId
  */
  @NotNull 
  @Schema(name = "paymentId", description = "Unique identifier for the transaction", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentId")
  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public PaymentResponse status(PaymentStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @NotNull @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("status")
  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  public PaymentResponse createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Timestamp of when the payment was created
   * @return createdAt
  */
  @NotNull @Valid 
  @Schema(name = "createdAt", description = "Timestamp of when the payment was created", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("createdAt")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentResponse paymentResponse = (PaymentResponse) o;
    return Objects.equals(this.paymentId, paymentResponse.paymentId) &&
        Objects.equals(this.status, paymentResponse.status) &&
        Objects.equals(this.createdAt, paymentResponse.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentId, status, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentResponse {\n");
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

