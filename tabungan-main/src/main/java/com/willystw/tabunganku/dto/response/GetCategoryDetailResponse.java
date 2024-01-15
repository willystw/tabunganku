package com.willystw.tabunganku.dto.response;

import com.willystw.tabunganku.dto.CategoryDto;

public class GetCategoryDetailResponse {

  private CategoryDto data;

  private String message;

  public GetCategoryDetailResponse() {
  }

  public CategoryDto getData() {
    return data;
  }

  public void setData(CategoryDto data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
