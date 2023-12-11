package com.ryu.tobybe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.common.enums.PaymentStatus;
import com.ryu.common.models.BaseResponse;
import com.ryu.common.sercurity.AuthUserDetail;
import com.ryu.tobybe.models.PaymentDto;
import com.ryu.tobybe.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @GetMapping("")
    public ResponseEntity<?> getPaymentRequests() {
        List<PaymentDto> result = paymentService.getPaymentRequests();
        BaseResponse<List<PaymentDto>> data = new BaseResponse<List<PaymentDto>>(result, 200 ,"");
        
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentRequestById(@PathVariable long id) {
        BaseResponse<Object> res;
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        PaymentDto data;
        try {
            data = paymentService.getPaymentRequestById(id);
            res = new BaseResponse<>(data, 200, "");
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            res = new BaseResponse<>(null, 500, e.getMessage());
        }
        
        return new ResponseEntity<>(res, httpStatus);
    }

    @GetMapping(value = "/history")
    public ResponseEntity<?> getPaymentHistory(@AuthenticationPrincipal AuthUserDetail userDetail) {
        List<PaymentDto> result = paymentService.getPaymentHistory(userDetail.getId());
        BaseResponse<List<PaymentDto>> data = new BaseResponse<List<PaymentDto>>(result, 200 ,"");

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value = "/aprrove/{id}")
    public ResponseEntity<?> approvePayment(@PathVariable long paymentId) {
        boolean result = paymentService.changeStatus(paymentId, PaymentStatus.COMPLETED);
        BaseResponse<Boolean> data = new BaseResponse<Boolean>(result, 200 ,"");

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value = "/reject/{id}")
    public ResponseEntity<?> rejectPayment(@PathVariable long paymentId) {
        boolean result = paymentService.changeStatus(paymentId, PaymentStatus.REJECT);
        BaseResponse<Boolean> data = new BaseResponse<Boolean>(result, 200 ,"");

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
