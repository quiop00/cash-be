package com.ryu.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserNotification  {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    protected String title;

    @Column
    protected String content;

    @Column
    protected String image;

    @Column
    protected String smallIcon;

    @Column
    private String isRead;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
