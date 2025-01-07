package com.basic.auth.controllers;


import com.basic.auth.models.user.User;
import com.basic.auth.services.impl.UserServiceImpl;
import com.basic.auth.web.dto.user.UserDto;
import com.basic.auth.web.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private UserMapper userMapper;


    @PutMapping("/update")
    public User updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userService.update(user);
    }



}
