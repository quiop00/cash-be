package com.ryu.tobybe.models;

import com.ryu.common.enums.PaymentStatus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentDto {

    private long point;

    private PaymentInfo paymentInfo;

    private PaymentStatus status;
}
