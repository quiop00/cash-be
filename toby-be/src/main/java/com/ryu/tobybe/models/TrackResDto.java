package com.ryu.tobybe.models;

import java.util.List;

import lombok.Data;

@Data
public class TrackResDto {
    private UserDto user;

    private List<TrackDto> track;
}
