package com.willystw.tabunganku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionSummaryDto extends TransactionDto {
  @JsonProperty("category_name")
  private String categoryName;

  @JsonProperty("category_type")
  private String categoryType;

  public TransactionSummaryDto() {
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryType() {
    return categoryType;
  }

  public void setCategoryType(String categoryType) {
    this.categoryType = categoryType;
  }
}
