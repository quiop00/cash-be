package com.ryu.tobybe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.common.entity.TrackingEntity;

public interface TrackRepository extends JpaRepository<TrackingEntity, Long>{

    
}
