package com.ryu.tobybe.models;

import java.time.ZonedDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrackDto {

    private String name;

    private long point;

    private ZonedDateTime date;
}
