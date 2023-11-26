package com.ryu.tobybe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.PaymentRequest;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
public interface PaymentRespositoty extends JpaRepository<PaymentRequest,Long>{
    
    @Query("SELECT pr FROM PaymentRequest pr WHERE pr.user.id = :userId")
    public List<PaymentRequest> findAllByUserId(@Param("userId") long userId);
}
