package com.ryu.tobybe.services;

import java.util.List;
import com.ryu.tobybe.models.SpinDto;

public interface SpinService {

    public List<SpinDto> getAllSpin();

    public SpinDto getSpin(long id) throws Exception;

    public SpinDto createSpin(SpinDto spinDto);

    public SpinDto updateSpin(long id, SpinDto spinDto) throws Exception;

    public boolean delete(long id) throws Exception;

    public int spin(long userId) throws Exception;

}
