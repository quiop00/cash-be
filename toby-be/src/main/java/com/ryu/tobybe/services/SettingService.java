package com.ryu.tobybe.services;

import com.ryu.common.entity.AppSecurityEntity;
import com.ryu.tobybe.models.OfferwallDto;
import com.ryu.tobybe.models.RewardDto;

public interface SettingService {
    
    public RewardDto getRewardConfig();

    public RewardDto updateRewardConfig(RewardDto data);

    public OfferwallDto getOfferwallConfig();

    public boolean updateOfferwallConfig(OfferwallDto data);

    public AppSecurityEntity getSecureConfig();

    public boolean updateSecureConfig(AppSecurityEntity data);
}
