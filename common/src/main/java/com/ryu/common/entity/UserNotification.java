package com.ryu.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserNotification extends NotificationEntity {

    @Column
    private String isRead;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
