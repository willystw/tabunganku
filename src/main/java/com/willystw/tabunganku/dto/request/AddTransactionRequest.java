package com.willystw.tabunganku.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class AddTransactionRequest {

  @NotNull(message = "Amount must not be null")
  @Min(value = 0, message = "Amount must be greater than 0")
  private Long amount;
  @NotNull(message = "Transaction Date must not be null")
  @JsonProperty("transaction_date")
  private LocalDate date;

  @NotNull(message = "Category ID must not be null")
  @JsonProperty("category_id")
  private Long categoryId;

  private String note;

  public AddTransactionRequest() {
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
