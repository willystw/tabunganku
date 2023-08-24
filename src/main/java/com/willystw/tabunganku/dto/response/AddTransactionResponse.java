package com.willystw.tabunganku.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTransactionResponse {
  @JsonProperty("transaction_id")
  private Long transactionId;

  private String message;

  public AddTransactionResponse() {
  }

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
