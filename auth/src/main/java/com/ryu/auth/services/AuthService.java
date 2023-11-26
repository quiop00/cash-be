package com.ryu.auth.services;

import com.ryu.auth.models.Login;
import com.ryu.auth.models.Registration;
import com.ryu.common.models.RequestOtp;

public interface AuthService {

    String login(final Login login);

    boolean register(final Registration registration);

    boolean verifyAccount(final RequestOtp data);

}
