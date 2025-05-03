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
 * RefundRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-03T16:01:17.482024-03:00[America/Sao_Paulo]", comments = "Generator version: 7.4.0")
public class RefundRequest {

  private String paymentId;

  private Integer amount;

  public RefundRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public RefundRequest(String paymentId, Integer amount) {
    this.paymentId = paymentId;
    this.amount = amount;
  }

  public RefundRequest paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

  /**
   * Unique identifier for the transaction to be refunded
   * @return paymentId
  */
  @NotNull 
  @Schema(name = "paymentId", description = "Unique identifier for the transaction to be refunded", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentId")
  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public RefundRequest amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Amount to be refunded
   * @return amount
  */
  @NotNull 
  @Schema(name = "amount", description = "Amount to be refunded", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RefundRequest refundRequest = (RefundRequest) o;
    return Objects.equals(this.paymentId, refundRequest.paymentId) &&
        Objects.equals(this.amount, refundRequest.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentId, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RefundRequest {\n");
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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

