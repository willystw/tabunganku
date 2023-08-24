package com.willystw.tabunganku.dto.response;

import com.willystw.tabunganku.dto.UserDto;

public class GetUserResponse {
  private UserDto data;

  public GetUserResponse() {
  }


  public UserDto getData() {
    return data;
  }

  public void setData(UserDto data) {
    this.data = data;
  }
}
