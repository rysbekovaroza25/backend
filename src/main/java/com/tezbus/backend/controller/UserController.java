package com.tezbus.backend.controller;

import com.tezbus.backend.dto.ReadUserProfileDto;
import com.tezbus.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}/profile")
    public ReadUserProfileDto getUserProfile(@PathVariable String id) {
        return userService.getUserProfile(id);
    }
}
