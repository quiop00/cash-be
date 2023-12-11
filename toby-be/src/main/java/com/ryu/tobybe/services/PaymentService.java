package com.ryu.tobybe.services;

import java.util.List;

import com.ryu.common.enums.PaymentStatus;
import com.ryu.tobybe.models.PaymentDto;

public interface PaymentService {

    public List<PaymentDto> getPaymentRequests();

    public PaymentDto getPaymentRequestById(long id) throws Exception;

    public List<PaymentDto> getPaymentHistory(long userId);

    public boolean changeStatus(long paymentId, PaymentStatus status);

}
