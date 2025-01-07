package com.basic.auth.controllers;

import com.basic.auth.models.user.User;
import com.basic.auth.services.impl.UserServiceImpl;
import com.basic.auth.web.dto.auth.AuthRequest;
import com.basic.auth.web.dto.user.UserDto;
import com.basic.auth.web.mappers.UserMapper;
import com.basic.auth.web.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserMapper userMapper;




    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            logger.info("Registering user: " + user);

            User userResult = userServiceImpl.create(user);
            return ResponseEntity.ok("Successfully registered user: " + userResult.getUsername());

        } catch (Exception e) {
            logger.severe("Error registering user: " + e.getMessage());
            Map<String, String> errors = new HashMap<>();
            errors.put("exception", e.getMessage());
            return ResponseEntity.badRequest().body(errors);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid AuthRequest authRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            logger.severe("Authentication failed for user: " + authRequest.getUsername() + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(token);
    }
}