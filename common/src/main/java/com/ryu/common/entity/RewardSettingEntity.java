package com.ryu.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class RewardSettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int dailyPoint;

    @Column
    private int dailySpinLimit;

    @Column
    private int videoLimit;

    @Column
    private int referBonus;

    @Column
    private String onesignalId;

    @Column
    private String onesignalApi;
}
