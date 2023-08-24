package com.willystw.tabunganku.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.willystw.tabunganku.dto.CategoryDto;
import java.util.List;

public class GetCategoryListResponse {
  @JsonProperty("category_list")
  private List<CategoryDto> categories;

  public GetCategoryListResponse() {
  }

  public List<CategoryDto> getCategories() {
    return categories;
  }

  public void setCategories(List<CategoryDto> categories) {
    this.categories = categories;
  }
}
