package com.ryu.tobybe.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.TrackingEntity;
import com.ryu.common.entity.UserEntity;
import com.ryu.common.repository.UserRepository;
import com.ryu.tobybe.models.TrackDto;
import com.ryu.tobybe.models.TrackResDto;
import com.ryu.tobybe.models.UserDto;
import com.ryu.tobybe.services.TrackService;

@Service
public class TrackServiceImpl implements TrackService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public TrackResDto getTrackData(long id) throws Exception{
        UserEntity userEntity = userRepository.findById(id).get();

        if (userEntity != null) {
            return convertEntityToDto(userEntity);
        }
        throw new Exception("User not found");
        
    }

    private TrackResDto convertEntityToDto(UserEntity userEntity) {
        TrackResDto dto = new TrackResDto();
        List<TrackingEntity> tracks = userEntity.getTrack();

        List<TrackDto> trackDtos = tracks.stream().map(e -> conTrackDto(e)).collect(Collectors.toList());
        dto.setUser(covertUserEntityToDto(userEntity));
        dto.setTrack(trackDtos);

        return dto;
    }

    private TrackDto conTrackDto(TrackingEntity trackingEntity) {
        return TrackDto.builder()
                .name(trackingEntity.getEarnFrom())
                .point(trackingEntity.getPoint())
                .date(trackingEntity.getDate())
                .build();
    }

    private UserDto covertUserEntityToDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .refer(entity.getRefer())
                .status(entity.getStatus())
                .image(entity.getImage())
                .point(entity.getCurrentPoint())
                .referCode(entity.getReferCode())
                .referUser(entity.getReferUser())
                .build();
    }
}
