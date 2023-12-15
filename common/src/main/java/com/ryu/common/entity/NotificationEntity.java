package com.ryu.common.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
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
