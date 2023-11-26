package com.ryu.tobybe.services.impl;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.GameHistory;
import com.ryu.common.entity.UserEntity;
import com.ryu.common.entity.WheelRewardEntity;
import com.ryu.tobybe.models.SpinDto;
import com.ryu.tobybe.repositories.SpinRepository;
import com.ryu.tobybe.repositories.UserRepository;
import com.ryu.tobybe.services.SpinService;

@Service
public class SpinServiceImpl implements SpinService {
    @Autowired
    private SpinRepository spinRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SpinDto> getAllSpin() {
        return spinRepository.findAll().stream().map(element -> convertEntityToDto(element))
                .collect(Collectors.toList());
    }

    public SpinDto getSpin(long id) throws Exception {
        Optional<WheelRewardEntity> eOptional = spinRepository.findById(id);
        if (eOptional.isPresent()) {
            return convertEntityToDto(eOptional.get());
        }

        throw new Exception("SPIN NOT FOUND");
    }

    public SpinDto createSpin(SpinDto spinDto) {
        WheelRewardEntity entity = spinDto.toEntity();

        entity = spinRepository.save(entity);

        spinDto.setId(entity.getId());

        return spinDto;
    }

    public SpinDto updateSpin(long id, SpinDto spinDto) throws Exception {
        WheelRewardEntity entity = spinRepository.findById(id).orElse(null);
        if (entity == null) {
            // todo modify to AppException
            throw new Exception("SPIN NOT FOUND");
        }
        entity.setType(false);
        entity.setPoint(spinDto.getPoint());
        entity.setTitle(spinDto.getTitle());

        spinRepository.save(entity);

        return spinDto;
    }

    public boolean delete(long id) throws Exception {
        Optional<WheelRewardEntity> entity = spinRepository.findById(id);
        if (entity.isPresent()) {
            spinRepository.deleteById(id);
        }

        throw new Exception("SPIN NOT FOUND");
    }

    @Override
    public int spin(long userId) throws Exception{
        UserEntity user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new Exception("USER NOT FOUND");
        }

        // check time spin of user
        GameHistory gameHistory = user.getHistory();

        if (gameHistory == null) {
            gameHistory = new GameHistory();
            gameHistory.setUser(user);
            gameHistory.setLastSpin(null);
        }

        if (isValidSpin(gameHistory.getLastSpin())) {
            Random random = new Random();
            int spinResult = random.nextInt(3) + 1;
            
            // TODO get spin value from DB
            // TODO Fix HARD CODE POINT
            int point = 4;
            user.setCurrentPoint(user.getCurrentPoint() + point);

            gameHistory.setLastSpin(ZonedDateTime.now());

            userRepository.save(user);

            return spinResult;
        }  

        return 0;
    }

    private boolean isValidSpin(ZonedDateTime date) {
        if (date == null) {
            return true;
        }

        LocalDate now = LocalDate.now();
        LocalDate newDate = date.toLocalDate();

        return now.isAfter(newDate);
    }

    private SpinDto convertEntityToDto(WheelRewardEntity entity) {
        return SpinDto.builder()
                .id(entity.getId())
                .isPoint(entity.isType())
                .point(entity.getPoint())
                .title(entity.getTitle())
                .build();
    }
}
