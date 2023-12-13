package com.ryu.tobybe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryu.tobybe.models.PasswordRequest;
import com.ryu.tobybe.models.PaymentInfo;
import com.ryu.tobybe.models.UserDto;

@Service
public interface UserService {

    public String updateStatus(long id) throws Exception;

    public List<UserDto> getVerifyUsers();

    public UserDto getUser(long id) throws Exception;

    public PaymentInfo getPaymentInfo(long id);

    public PaymentInfo updatePaymentInfo(PaymentInfo data) throws Exception;

    public boolean changePassword(long userId, PasswordRequest data);

}
