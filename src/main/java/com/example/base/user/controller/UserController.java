package com.example.base.user.controller;


import com.example.base.user.dto.LoginDto;
import com.example.base.user.dto.SignupDto;
import com.example.base.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public void signup(@RequestBody SignupDto signupDto) {
        userService.joinUser(signupDto);

    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {
        userService.loginUser(loginDto);

    }

}
