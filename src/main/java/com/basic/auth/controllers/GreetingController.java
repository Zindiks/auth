package com.basic.auth.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {

    private static final Logger logger = Logger.getLogger(GreetingController.class.getName());

    @GetMapping("/hello")
    @ResponseBody
    public Map<String, String> greeting(HttpServletRequest request, HttpServletResponse response) {
        String clientIp = request.getRemoteAddr();
        System.out.println("Client IP: " + clientIp);

        response.setStatus(201);
        Map<String,String> responseBody = new HashMap<>();

        responseBody.put("message", "Hello, World!");
        responseBody.put("1"  ,"34");


        return responseBody;

    }



}
