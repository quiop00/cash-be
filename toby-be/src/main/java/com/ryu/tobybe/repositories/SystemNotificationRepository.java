package com.ryu.tobybe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.SystemNotification;


@Repository
public interface SystemNotificationRepository extends JpaRepository<SystemNotification, Long>{
    
    // @Query("SELECT n FROM SystemNotification n WHERE n.user.id = :userId")
    // public List<SystemNotification> findAllByUserId(@Param("userId") long userId);
}
