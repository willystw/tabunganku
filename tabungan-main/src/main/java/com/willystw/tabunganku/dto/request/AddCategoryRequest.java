package com.willystw.tabunganku.dto.request;

import com.willystw.tabunganku.model.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddCategoryRequest {
  @NotBlank(message = "Category Name must not be empty")
  @JsonProperty("category_name")
  private String name;

  @NotNull(message = "Transaction Type must be valid")
  @JsonProperty("transaction_type")
  private TransactionType type;

  public AddCategoryRequest() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }
}
