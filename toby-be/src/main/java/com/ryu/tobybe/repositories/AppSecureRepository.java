package com.ryu.tobybe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.AppSecurityEntity;

@Repository
public interface AppSecureRepository extends JpaRepository<AppSecurityEntity, Long>{
    
}
