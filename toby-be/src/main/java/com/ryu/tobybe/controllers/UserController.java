package com.ryu.tobybe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.models.BaseResponse;
import com.ryu.common.sercurity.AuthUserDetail;
import com.ryu.tobybe.models.PasswordRequest;
import com.ryu.tobybe.models.PaymentInfo;
import com.ryu.tobybe.models.UserDto;
import com.ryu.tobybe.services.UserService;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getVerifyUsers() {
        List<UserDto> users = userService.getVerifyUsers();
        BaseResponse<List<UserDto>> res = new BaseResponse<>(users, 200, "");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable long id) {
        BaseResponse<Object> res;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            UserDto user = userService.getUser(id);
            res = new BaseResponse<>(user, 200, "");
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            res = new BaseResponse<>(null, 500, e.getMessage());
        }

        return new ResponseEntity<>(res, httpStatus);
    }

    @PutMapping("/update-status/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> blockUser(@PathVariable long id) {
        String status;
        BaseResponse<String> res;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        try {
            status = userService.updateStatus(id);
            res = new BaseResponse<>(status, 200, "");
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            res = new BaseResponse<>(null, 500, e.getMessage());
        }

        return new ResponseEntity<>(res, httpStatus);
    }

    @GetMapping("/paymentInfo/{id}")
    public ResponseEntity<?> getPaymentInfo(@PathVariable long id) {
        PaymentInfo info = userService.getPaymentInfo(id);
        BaseResponse<PaymentInfo> res = new BaseResponse<>(info, 200, "");
        return new ResponseEntity<>(res, HttpStatus.OK);
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

    @PutMapping("change-password")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal AuthUserDetail user, @RequestBody PasswordRequest data) {
        boolean result = userService.changePassword(user.getId(), data);
        BaseResponse<Boolean> res = new BaseResponse<>(result, 200, "");
        
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
