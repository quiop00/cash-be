package com.ryu.auth.services;

import com.ryu.auth.models.Login;
import com.ryu.auth.models.LoginResponse;
import com.ryu.auth.models.Registration;
import com.ryu.common.models.RequestOtp;

public interface AuthService {

    LoginResponse login(final Login login);

    LoginResponse loginAdmin(final Login login);

    String register(final Registration registration);

    boolean verifyAccount(final RequestOtp data);

}
