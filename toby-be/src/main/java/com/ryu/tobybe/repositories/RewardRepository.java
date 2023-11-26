package com.ryu.tobybe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.RewardSettingEntity;

@Repository
public interface RewardRepository extends JpaRepository<RewardSettingEntity, Long>{
    
}
