package com.ryu.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.UserEntity;
import com.ryu.common.enums.EStatus;

@Repository
public interface UserRepository extends JpaRepository< UserEntity, Long>{

    public Optional<UserEntity> findByEmail(String email);

    public List<UserEntity> findAllByStatusIn(List<EStatus> statuses);

    public List<UserEntity> findAllByReferUser(long referId);

    List<UserEntity> findAllByUsernameAndStatus(String username, EStatus status);

    List<UserEntity> findAllByEmailAndStatus(String email, EStatus status);

    UserEntity findByEmailAndPassword(String email, String password);

    List<UserEntity> findByUsername(String userName);

    UserEntity findByReferCode(String referCode);
}
