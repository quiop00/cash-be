package com.ryu.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestOtp {
    private String email;
    private String otp;
}
