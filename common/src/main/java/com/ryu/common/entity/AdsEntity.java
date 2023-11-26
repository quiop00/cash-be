package com.ryu.common.entity;

import com.ryu.common.enums.AdsType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class AdsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String ironSourceAppKey;

    @Column
    private String vungleAppId;

    @Column
    private String unityGameId;

    @Column
    @Enumerated(EnumType.STRING)
    private AdsType bannerAdsType;

    @Column
    private String applovinBannerId;

    @Column
    private String unityBannerId;

    @Column
    @Enumerated(EnumType.STRING)
    private AdsType inverstitalType;

    @Column
    private String applovinInverstitalId;

    @Column
    private String unityInverstitalId;

    @Column
    @Enumerated(EnumType.STRING)
    private AdsType rewardedAdsType;

    @Column
    private String applovinRewardedId;

    @Column
    private String unityRewardedId;

    @Column
    private String admobRewardId;
}
