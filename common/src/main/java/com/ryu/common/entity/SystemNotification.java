package com.ryu.common.entity;

import java.time.ZonedDateTime;

import com.ryu.common.enums.SystemNoticeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
public class SystemNotification extends NotificationEntity {
    
    @Column
    private String status;

    @Column
    private ZonedDateTime noticeAt;

    @Column
    @Enumerated(EnumType.STRING)
    private SystemNoticeType type;
}
