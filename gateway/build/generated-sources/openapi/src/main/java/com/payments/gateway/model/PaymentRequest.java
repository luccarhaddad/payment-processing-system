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
 * PaymentRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-03T16:01:17.482024-03:00[America/Sao_Paulo]", comments = "Generator version: 7.4.0")
public class PaymentRequest {

  private Integer amount;

  private String currency;

  private String paymentMethod;

  private String customerId;

  private String merchantReference;

  public PaymentRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PaymentRequest(Integer amount, String currency, String paymentMethod, String customerId, String merchantReference) {
    this.amount = amount;
    this.currency = currency;
    this.paymentMethod = paymentMethod;
    this.customerId = customerId;
    this.merchantReference = merchantReference;
  }

  public PaymentRequest amount(Integer amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Amount to be processed
   * @return amount
  */
  @NotNull 
  @Schema(name = "amount", description = "Amount to be processed", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("amount")
  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public PaymentRequest currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Currency code (e.g., USD, EUR)
   * @return currency
  */
  @NotNull 
  @Schema(name = "currency", description = "Currency code (e.g., USD, EUR)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public PaymentRequest paymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  /**
   * Payment method (e.g., CREDIT_CARD, BANK_TRANSFER)
   * @return paymentMethod
  */
  @NotNull 
  @Schema(name = "paymentMethod", description = "Payment method (e.g., CREDIT_CARD, BANK_TRANSFER)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("paymentMethod")
  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public PaymentRequest customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Unique identifier for the customer
   * @return customerId
  */
  @NotNull 
  @Schema(name = "customerId", description = "Unique identifier for the customer", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("customerId")
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public PaymentRequest merchantReference(String merchantReference) {
    this.merchantReference = merchantReference;
    return this;
  }

  /**
   * Unique identifier for the merchant
   * @return merchantReference
  */
  @NotNull 
  @Schema(name = "merchantReference", description = "Unique identifier for the merchant", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("merchantReference")
  public String getMerchantReference() {
    return merchantReference;
  }

  public void setMerchantReference(String merchantReference) {
    this.merchantReference = merchantReference;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentRequest paymentRequest = (PaymentRequest) o;
    return Objects.equals(this.amount, paymentRequest.amount) &&
        Objects.equals(this.currency, paymentRequest.currency) &&
        Objects.equals(this.paymentMethod, paymentRequest.paymentMethod) &&
        Objects.equals(this.customerId, paymentRequest.customerId) &&
        Objects.equals(this.merchantReference, paymentRequest.merchantReference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency, paymentMethod, customerId, merchantReference);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentRequest {\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    merchantReference: ").append(toIndentedString(merchantReference)).append("\n");
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

