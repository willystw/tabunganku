package com.willystw.tabunganku.controller;

import com.willystw.tabunganku.dto.UserDto;
import com.willystw.tabunganku.dto.request.AddUserRequest;
import com.willystw.tabunganku.dto.response.AddUserResponse;
import com.willystw.tabunganku.dto.response.GetUserResponse;
import com.willystw.tabunganku.mapper.UserMapper;
import com.willystw.tabunganku.model.User;
import com.willystw.tabunganku.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  @PostMapping("/add")
  public AddUserResponse insertUserData(
      @RequestBody AddUserRequest userData
  ) {
    Long id = userService.insertData(userData.getUsername(), userData.getEmail());
    AddUserResponse response = new AddUserResponse();
    response.setUserId(id);
    if (id == null) {
      response.setMessage("Failed to add new user.");
    }
    return response;
  }

  @Override
  @GetMapping("/{userId}/get")
  public GetUserResponse getUserData(
      @PathVariable Long userId) {
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
