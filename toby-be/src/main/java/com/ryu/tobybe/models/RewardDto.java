package com.ryu.tobybe.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardDto {
    private long id;

    private int dailyPoint;

    private int dailySpinLimit;

    private int videoLimit;

    private int referBonus;

    private String onesignalId;

    private String onesignalApi;
}
