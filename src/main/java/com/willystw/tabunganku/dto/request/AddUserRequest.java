package com.willystw.tabunganku.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddUserRequest {
  @NotBlank(message = "Username must not blank")
  @Size.List({
      @Size(min = 4, message = "Username must be between 4 and 20 characters long"),
      @Size(max = 20, message = "Username must be between 4 and 20 characters long")
  })
  private String username;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Invalid email")
  private String email;

  public AddUserRequest() {
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
