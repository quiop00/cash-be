package com.ryu.tobybe.services;

import com.ryu.common.entity.AdsEntity;

public interface AdsService {
    
    public AdsEntity getAdsConfig();

    public AdsEntity updateAdsConfig(AdsEntity ads);

}
