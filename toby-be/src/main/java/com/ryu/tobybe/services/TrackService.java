package com.ryu.tobybe.services;

import com.ryu.tobybe.models.TrackResDto;

public interface TrackService {

    public TrackResDto getTrackData(long id) throws Exception;
}
