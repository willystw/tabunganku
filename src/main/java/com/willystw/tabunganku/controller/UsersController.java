package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.UserDto;
import com.willystw.tabunganku.dto.response.GetUserResponse;
import com.willystw.tabunganku.mapper.UserMapper;
import com.willystw.tabunganku.model.User;
import com.willystw.tabunganku.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController implements IUsersController {

  private final UserService userService;

  public UsersController(UserService userService) {
    this.userService = userService;
  }

  @Override
  @GetMapping("/{userId}/get")
  public GetUserResponse getUserData(@PathVariable Long userId) {
    User userData = userService.getById(userId);

    if (userData == null) {
      return null;
    }

    UserDto dto = UserMapper.INSTANCE.userToUserDto(userData);

    GetUserResponse response = new GetUserResponse();
    response.setData(dto);
    return response;
  }
}
