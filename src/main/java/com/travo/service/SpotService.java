package com.travo.service;

import com.travo.dto.HotSpotDTO;
import com.travo.dto.SpotDTO;
import com.travo.model.Spot;
import com.travo.model.User;

import java.util.ArrayList;
import java.util.List;

public interface SpotService {
    List<SpotDTO> findAllSpot();
    SpotDTO findSpotDTOById(Long Id);
    boolean isSpotExisted(SpotDTO spotDTO);
    Spot findSpotById(Long id);
    void saveSpot(SpotDTO spot);
//    List<HotSpotDTO> findHotSpot(User user);
    void disableSpotById(Long id);
}
