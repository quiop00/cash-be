package com.ryu.tobybe.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.ReferQuest;
import com.ryu.common.entity.UserEntity;
import com.ryu.common.repository.UserRepository;
import com.ryu.tobybe.models.ReferDto;
import com.ryu.tobybe.models.ReferQuestDto;
import com.ryu.tobybe.repositories.ReferQuestRepository;

import com.ryu.tobybe.services.ReferService;

@Service
public class ReferServiceImpl implements ReferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferQuestRepository referQuestRepository;

    private final static int REFER_POINT = 50;

    @Override
    public ReferDto getReferData(long userId) throws Exception{
        UserEntity user = userRepository.findById(userId).get();
        if (user != null) {
            List<UserEntity> referUsers = userRepository.findAllByReferUser(userId);
            ReferDto referDto = new ReferDto(0, 0, user.getReferCode());
            if (referUsers.isEmpty()) {
                return referDto;
            }

            referDto.setRefer(referUsers.stream().filter(e -> e.getReferUser() == userId).collect(Collectors.toList()).size());
            referDto.setInvited(referUsers.size());

            return referDto;
        }
        throw new Exception("User not found");
    }

    // TODO Update for security, retry when error
    @Override
    public boolean referUser(long referUser) throws Exception {
        UserEntity user = userRepository.findById(referUser).get();
        if (user != null) {
            user.setCurrentPoint(user.getCurrentPoint() + REFER_POINT);
            userRepository.save(user);
            return true;
        }

        throw new Exception("REFER FAIL");
    }

    @Override
    public ReferQuestDto getReferQuest(long id) throws Exception{
        ReferQuest referQuest = referQuestRepository.findById(id).get();
        if (referQuest != null) {
            return convertEntityToDto(referQuest);
        }
        throw new Exception("ReferQuest NOT FOUND");
    }

    @Override
    public List<ReferQuestDto> getReferQuests() {
        return referQuestRepository.findAll().stream().map(e -> convertEntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public ReferQuestDto createReferQuest(ReferQuestDto data) {
        ReferQuest quest = convertDtoToEntity(data);
        quest = referQuestRepository.save(quest);

        data.setId(quest.getId());
        
        return data;
    }

    @Override
    public ReferQuestDto updateReferQuest(ReferQuestDto data) {
        ReferQuest quest = convertDtoToEntity(data);
        referQuestRepository.save(quest);
        
        return data;
    }

    @Override
    public boolean delete(long id) {
        try {
            referQuestRepository.deleteById(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private ReferQuestDto convertEntityToDto(ReferQuest referQuest) {
        return ReferQuestDto
                .builder()
                .id(referQuest.getId())
                .name(referQuest.getName())
                .invited(referQuest.getInvited())
                .point(referQuest.getPoint())
                .build();
    }

    private ReferQuest convertDtoToEntity(ReferQuestDto data) {
        ReferQuest quest = new ReferQuest();
        quest.setId(data.getId());
        quest.setName(data.getName());
        quest.setInvited(data.getPoint());
        quest.setPoint(data.getPoint());

        return quest;
                
    }
    
}
