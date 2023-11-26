package com.ryu.tobybe.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReferDto {
    private int refer;

    private int invited;

    private String referCode;
}
