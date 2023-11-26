package com.ryu.auth.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonTypeName("user")
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String image;
    private String role;
}
