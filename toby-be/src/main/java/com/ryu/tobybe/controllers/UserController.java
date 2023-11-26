package com.ryu.tobybe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.tobybe.models.PaymentInfo;
import com.ryu.tobybe.models.UserDto;
import com.ryu.tobybe.services.UserService;

@RestController("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getVerifyUsers() {
        List<UserDto> users = userService.getVerifyUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/paymentInfo/{id}")
    public ResponseEntity<?> getPaymentInfo(@PathVariable long id) {
        PaymentInfo info = userService.getPaymentInfo(id);
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @PutMapping("/paymentInfo")
    public ResponseEntity<?> updatePaymentInfo(@RequestBody PaymentInfo data) {
        try {
            PaymentInfo info = userService.updatePaymentInfo(data);
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
