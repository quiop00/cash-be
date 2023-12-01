package com.ryu.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.auth.models.Login;
import com.ryu.auth.models.LoginResponse;
import com.ryu.auth.models.Registration;
import com.ryu.auth.services.AuthService;
import com.ryu.common.models.BaseResponse;
import com.ryu.common.models.BaseResponse;
import com.ryu.common.models.RequestOtp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService uAuthService;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Login login) {
        LoginResponse data = uAuthService.login(login);
        BaseResponse<Object> response = new BaseResponse<Object>(data, 200, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@Valid @RequestBody Login login) {
        LoginResponse data = uAuthService.loginAdmin(login);
        BaseResponse<Object> response = new BaseResponse<Object>(data, 200, "");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Registration registration) {
        String data = uAuthService.register(registration);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value="/verify")
    public boolean verify(@RequestBody RequestOtp data) {
        return uAuthService.verifyAccount(data);
    }
    
}
