package com.ryu.tobybe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.AdsEntity;
import com.ryu.tobybe.repositories.AdsRepository;
import com.ryu.tobybe.services.AdsService;

@Service
public class AdsServiceImpl implements AdsService {
    
    @Autowired
    private AdsRepository adsRepository;

    @Override
    public AdsEntity getAdsConfig() {
        return adsRepository.findAll().stream().findFirst().get();
    }

    @Override
    public AdsEntity updateAdsConfig(AdsEntity ads) {
        return adsRepository.save(ads);
    }
    
}
