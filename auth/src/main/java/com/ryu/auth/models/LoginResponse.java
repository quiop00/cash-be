package com.ryu.auth.models;

import com.ryu.common.enums.ERole;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private ERole role;
}
