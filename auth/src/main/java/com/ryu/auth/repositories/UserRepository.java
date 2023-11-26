package com.ryu.auth.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryu.common.entity.UserEntity;
import com.ryu.common.enums.EStatus;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByUsernameAndStatus(String username, EStatus status);

    List<UserEntity> findAllByEmailAndStatus(String email, EStatus status);

    UserEntity findByEmailAndPassword(String email, String password);

    List<UserEntity> findByEmail(String email);

    List<UserEntity> findByUsername(String userName);

    UserEntity findByReferCode(String referCode);

}
