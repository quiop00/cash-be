package com.ryu.tobybe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.AppSecurityEntity;
import com.ryu.common.entity.OfferwallEntity;
import com.ryu.common.entity.RewardSettingEntity;
import com.ryu.tobybe.models.OfferwallDto;
import com.ryu.tobybe.models.RewardDto;
import com.ryu.tobybe.repositories.AppSecureRepository;
import com.ryu.tobybe.repositories.OfferwallRepository;
import com.ryu.tobybe.repositories.RewardRepository;
import com.ryu.tobybe.services.SettingService;

@Service
public class SettingServiceImpl implements SettingService{

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private OfferwallRepository offerwallRepository;

    @Autowired
    private AppSecureRepository secureRepository;

    @Override
    public RewardDto getRewardConfig() {
        RewardSettingEntity entity = rewardRepository.findAll().stream().findFirst().get();
        if (entity != null) {
            return convertRewardEntityToDto(entity);
        }

        return new RewardDto();
    }

    @Override
    public RewardDto updateRewardConfig(RewardDto data) {
        RewardSettingEntity entity = convertDtoToRewardEntity(data);
        rewardRepository.save(entity);
        
        return data;
    }

    @Override
    public OfferwallDto getOfferwallConfig() {
        OfferwallEntity entity = offerwallRepository.findAll().stream().findFirst().get();
         if (entity != null) {
            return convertOfferwallEntityToDto(entity);
        }

        return new OfferwallDto();
    }

    @Override
    public boolean updateOfferwallConfig(OfferwallDto data) {
        OfferwallEntity entity = convertDtoToOfferwallDto(data);
        offerwallRepository.save(entity);

        return true;
    }

    @Override
    public AppSecurityEntity getSecureConfig() {
       return secureRepository.findAll().stream().findFirst().get();
    }

    @Override
    public boolean updateSecureConfig(AppSecurityEntity data) {
        secureRepository.save(data);
        return true;
    }

    private RewardDto convertRewardEntityToDto(RewardSettingEntity entity) {
        return RewardDto
                .builder()
                .id(entity.getId())
                .dailyPoint(entity.getDailyPoint())
                .dailySpinLimit(entity.getDailySpinLimit())
                .videoLimit(entity.getVideoLimit())
                .referBonus(entity.getReferBonus())
                .onesignalId(entity.getOnesignalId())
                .onesignalApi(entity.getOnesignalApi())
                .build();
    }

    private RewardSettingEntity convertDtoToRewardEntity(RewardDto dto) {
        return RewardSettingEntity
                .builder()
                .id(dto.getId())
                .dailyPoint(dto.getDailyPoint())
                .dailySpinLimit(dto.getDailySpinLimit())
                .videoLimit(dto.getVideoLimit())
                .referBonus(dto.getReferBonus())
                .onesignalId(dto.getOnesignalId())
                .onesignalApi(dto.getOnesignalApi())
                .build();
    }

    private OfferwallDto convertOfferwallEntityToDto(OfferwallEntity entity) {
        return OfferwallDto
                .builder()
                .id(entity.getId())
                .offerAppId(entity.getOfferAppId())
                .offerKey(entity.getOfferKey())
                .offerPublisherId(entity.getOfferPublisherId())
                .offerPostbackUrl(entity.getOfferPostbackUrl())
                .adGetMediaCode(entity.getAdGetMediaCode())
                .adGetMediaPostbackUrl(entity.getAdGetMediaPostbackUrl())
                .ayetPostbackUrl(entity.getAyetPostbackUrl())
                .build();
    }

    private OfferwallEntity convertDtoToOfferwallDto(OfferwallDto dto) {
        return OfferwallEntity
                .builder()
                .id(dto.getId())
                .offerAppId(dto.getOfferAppId())
                .offerKey(dto.getOfferKey())
                .offerPublisherId(dto.getOfferPublisherId())
                .offerPostbackUrl(dto.getOfferPostbackUrl())
                .adGetMediaCode(dto.getAdGetMediaCode())
                .adGetMediaPostbackUrl(dto.getAdGetMediaPostbackUrl())
                .ayetPostbackUrl(dto.getAyetPostbackUrl())
                .build();
    }
    
}
