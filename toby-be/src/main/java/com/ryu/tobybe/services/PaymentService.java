package com.ryu.tobybe.services;

import java.util.List;

import com.ryu.tobybe.models.PaymentDto;

public interface PaymentService {

    public List<PaymentDto> getPaymentRequests();

    public List<PaymentDto> getPaymentHistory(long userId);

    public boolean changeStatus(long paymentId, boolean isAccepted);

}
