package com.ryu.tobybe.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.PaymentDetail;
import com.ryu.common.entity.PaymentRequest;
import com.ryu.common.enums.PaymentStatus;
import com.ryu.tobybe.models.PaymentDto;
import com.ryu.tobybe.models.PaymentInfo;
import com.ryu.tobybe.repositories.PaymentRespositoty;
import com.ryu.tobybe.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRespositoty paymentRespositoty;

    @Override
    public List<PaymentDto> getPaymentRequests() {
        return paymentRespositoty.findAll().stream().map(e -> convertEntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public PaymentDto getPaymentRequestById(long id) throws Exception{
        PaymentRequest request = paymentRespositoty.findById(id).get();
        if (request == null) {
            throw new Exception("DATA NOT FOUND");
        }
        return convertEntityToDto(request);
    }

    @Override
    public List<PaymentDto> getPaymentHistory(long userId) {
        List<PaymentRequest> paymentRequests = paymentRespositoty.findAllByUserId(userId);

        return paymentRequests.stream().map(e -> convertEntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public boolean changeStatus(long paymentId, PaymentStatus status) {
        PaymentRequest paymentRequest = paymentRespositoty.findById(paymentId).get();
        if (paymentRequest != null) {
            paymentRequest.setStatus(status);
            paymentRespositoty.save(paymentRequest);
            return true;
        }

        return false;
    }

    private PaymentDto convertEntityToDto(PaymentRequest paymentRequest) {

        PaymentDetail detail = paymentRequest.getUser().getPaymentInfo();
        PaymentInfo info = PaymentInfo.builder()
                            .userId(detail.getUser().getId())
                            .fullname(detail.getFullname())
                            .method(detail.getMethod())
                            .value(detail.getNumber())
                            .build();

        return PaymentDto.builder()
                    .status(paymentRequest.getStatus())
                    .point(paymentRequest.getAmount())
                    .paymentInfo(info)
                    .build();
    }

}
