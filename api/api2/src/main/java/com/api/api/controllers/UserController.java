package com.api.api.controllers;

import com.api.api.StaticObj;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/userId")
    public int userId(){
        return StaticObj.user.getId();
    }
}
