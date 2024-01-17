package com.willystw.tabunganku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDto {
  @JsonProperty("category_id")
  private Long id;

  @JsonProperty("category_name")
  private String name;

  @JsonProperty("category_type")
  private String type;

  public CategoryDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
