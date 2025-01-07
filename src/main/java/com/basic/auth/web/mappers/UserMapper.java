package com.basic.auth.web.mappers;


import com.basic.auth.models.user.User;
import com.basic.auth.web.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
    User toEntity(UserDto dto);
    UserDto toDto(User entity);
}



