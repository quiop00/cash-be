package com.ryu.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spin")
@Builder
@Setter
@Getter
public class WheelRewardEntity extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private boolean type;

    @Column(name = "uid")
    private String uid;

    @Column(name = "point")
    private int point;
}
