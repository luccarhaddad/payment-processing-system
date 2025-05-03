package com.payments.gateway.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CancelRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-03T16:01:17.482024-03:00[America/Sao_Paulo]", comments = "Generator version: 7.4.0")
public class CancelRequest {

  private String paymentId;

  public CancelRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CancelRequest(String paymentId) {
    this.paymentId = paymentId;
  }

  public CancelRequest paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  /**
   * Unique identifier for the transaction to be canceled
   * @return paymentId
  */
  @NotNull 
  @Schema(name = "paymentId", description = "Unique identifier for the transaction to be canceled", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentId")
  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CancelRequest cancelRequest = (CancelRequest) o;
    return Objects.equals(this.paymentId, cancelRequest.paymentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CancelRequest {\n");
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
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

