package com.ryu.tobybe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.WheelRewardEntity;

@Repository
public interface SpinRepository extends JpaRepository<WheelRewardEntity, Long>{
    
}
