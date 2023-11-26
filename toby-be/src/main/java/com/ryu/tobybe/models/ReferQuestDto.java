package com.ryu.tobybe.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReferQuestDto {
    private Long id;

    private String name;

    private int invited;

    private int point;
}
