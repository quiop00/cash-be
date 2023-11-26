package com.ryu.tobybe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.AdsEntity;

@Repository
public interface AdsRepository extends JpaRepository<AdsEntity, Long>{
    
}
