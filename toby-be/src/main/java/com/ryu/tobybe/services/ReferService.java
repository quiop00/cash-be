package com.ryu.tobybe.services;

import java.util.List;

import com.ryu.tobybe.models.ReferDto;
import com.ryu.tobybe.models.ReferQuestDto;

public interface ReferService {
    
    public ReferDto getReferData(long userId) throws Exception;

    public boolean referUser(long referCode) throws Exception;

    public ReferQuestDto getReferQuest(long id) throws Exception;

    public List<ReferQuestDto> getReferQuests();

    public ReferQuestDto createReferQuest(ReferQuestDto data);

    public ReferQuestDto updateReferQuest(ReferQuestDto data);

    public boolean delete(long id);
}
