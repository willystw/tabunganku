package com.willystw.tabunganku.mapper;

import com.willystw.tabunganku.dto.UserDto;
import com.willystw.tabunganku.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "id", source = "userId")
  @Mapping(target = "username", source = "userName")
  UserDto userToUserDto(User category);
}
