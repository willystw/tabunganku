package com.willystw.tabunganku.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
  @JsonProperty("user_id")
  private Long id;

  private String username;

  private String email;

  public UserDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
