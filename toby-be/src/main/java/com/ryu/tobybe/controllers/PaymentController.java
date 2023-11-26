package com.ryu.tobybe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.sercurity.AuthUserDetail;
import com.ryu.tobybe.models.PaymentDto;
import com.ryu.tobybe.services.PaymentService;

@RestController("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @GetMapping(value = "/")
    public ResponseEntity<?> getPaymentRequests() {
        List<PaymentDto> result = paymentService.getPaymentRequests();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/history")
    public ResponseEntity<?> getPaymentHistory(@AuthenticationPrincipal AuthUserDetail userDetail) {
        List<PaymentDto> result = paymentService.getPaymentHistory(userDetail.getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/change-status/{id}")
    public ResponseEntity<?> changeStatusPayment(@PathVariable long paymentId) {
        boolean result = paymentService.changeStatus(paymentId, false);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
