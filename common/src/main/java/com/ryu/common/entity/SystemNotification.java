package com.ryu.common.entity;

import java.time.ZonedDateTime;

import com.ryu.common.enums.SystemNoticeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
public class SystemNotification {

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
    private String status;

    @Column
    private ZonedDateTime noticeAt;

    @Column
    @Enumerated(EnumType.STRING)
    private SystemNoticeType type;
}
