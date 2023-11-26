package com.ryu.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class NotificationEntity extends BaseEntity {
    
    @Column
    protected String title;

    @Column
    protected String content;

    @Column
    protected String image;

    @Column
    protected String smallIcon;
}
