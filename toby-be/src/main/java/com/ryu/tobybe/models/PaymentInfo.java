package com.ryu.tobybe.models;

import com.ryu.common.enums.PaymentMethod;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentInfo {

    private long userId;

    private String fullname;

    // Phone number/ email
    private String value;

    //private 
    private PaymentMethod method;
}
