package com.ryu.tobybe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.OfferwallEntity;

@Repository
public interface OfferwallRepository extends JpaRepository<OfferwallEntity, Long>{
    
}
