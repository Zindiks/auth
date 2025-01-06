package com.basic.auth.web.mappers;


import com.basic.auth.models.User;
import com.basic.auth.web.dto.user.UserDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto> {
}



