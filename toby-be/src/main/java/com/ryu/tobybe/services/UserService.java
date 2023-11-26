package com.ryu.tobybe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryu.tobybe.models.PaymentInfo;
import com.ryu.tobybe.models.UserDto;

@Service
public interface UserService {

    public boolean updateStatus(long id, boolean isBlock) throws Exception;

    public List<UserDto> getVerifyUsers();

    public PaymentInfo getPaymentInfo(long id);

    public PaymentInfo updatePaymentInfo(PaymentInfo data) throws Exception;

}
