package com.example.api2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.api2.StaticObj.user;
@RestController
public class UserController {
    @GetMapping("/userId")
    public int userId(){
        return user.getId();
    }
}
