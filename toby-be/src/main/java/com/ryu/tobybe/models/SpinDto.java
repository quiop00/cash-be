package com.ryu.tobybe.models;

import com.ryu.common.entity.WheelRewardEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpinDto {
    private long id;

    private String uid;

    private String title;

    private boolean isPoint;

    private int point;

    public WheelRewardEntity toEntity() {
        WheelRewardEntity entity = WheelRewardEntity.builder()
            .uid(uid)
            .point(point)
            .title(title)
            .type(isPoint)
            .build();

        entity.setId(id);

        return entity;
    }
}
