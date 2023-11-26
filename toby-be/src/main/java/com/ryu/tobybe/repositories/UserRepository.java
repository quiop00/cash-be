package com.ryu.tobybe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.UserEntity;
import com.ryu.common.enums.EStatus;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    public List<UserEntity> findAllByStatusIn(List<EStatus> statuses);

    public List<UserEntity> findAllByReferUser(long referId);
}
