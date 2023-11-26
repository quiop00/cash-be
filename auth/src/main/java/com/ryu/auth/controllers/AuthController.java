package com.ryu.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.auth.models.Login;
import com.ryu.auth.models.Registration;
import com.ryu.auth.services.AuthService;
import com.ryu.common.models.RequestOtp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService uAuthService;
    
    @PostMapping("/login")
    public String login(@Valid @RequestBody Login login) {
        return uAuthService.login(login);
    }

    @PostMapping("/register")
    public boolean register(@Valid @RequestBody Registration registration) {
        return uAuthService.register(registration);
    }

    @PostMapping(value="/verify")
    public boolean verify(@RequestBody RequestOtp data) {
        return uAuthService.verifyAccount(data);
    }
    
}
