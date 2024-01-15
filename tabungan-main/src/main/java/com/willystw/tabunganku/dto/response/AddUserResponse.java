package com.willystw.tabunganku.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserResponse {

  @JsonProperty("user_id")
  private Long userId;

  private String message;

  public AddUserResponse() {
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
