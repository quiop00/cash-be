package com.ryu.tobybe.models;

import com.ryu.common.enums.ERole;
import com.ryu.common.enums.EStatus;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {
    private long id;

    private String username;

    private String email;

    private String image;

    private ERole role;

    private EStatus status;

    private int refer;

    private long point;

    private String referCode;

    private long referUser;
}
