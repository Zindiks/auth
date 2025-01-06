package com.basic.auth.web.dto.user;

import com.basic.auth.web.validation.OnCreate;
import com.basic.auth.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "Id is required", groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "Email is required", groups = {OnCreate.class, OnUpdate.class})
    @Email(message = "Please provide a valid email", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotBlank(message = "Username is required", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is required", groups = OnCreate.class)
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters", groups = OnCreate.class)
    private String password;
}