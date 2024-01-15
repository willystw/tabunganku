package com.willystw.tabunganku.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddCategoryResponse {

  @JsonProperty("category_id")
  private Long categoryId;

  private String message;

  public AddCategoryResponse() {
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
