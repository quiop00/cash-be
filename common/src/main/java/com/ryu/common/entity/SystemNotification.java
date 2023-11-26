package com.ryu.common.entity;

import java.time.ZonedDateTime;

import com.ryu.common.enums.SystemNoticeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
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
