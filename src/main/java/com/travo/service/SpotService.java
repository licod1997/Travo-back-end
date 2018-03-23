package com.travo.service;

import com.travo.dto.SpotDTO;
import com.travo.model.Spot;

import java.util.ArrayList;
import java.util.List;

public interface SpotService {
    List<SpotDTO> findAllSpot();
    SpotDTO findSpotById(Long Id);
    boolean isSpotExisted(SpotDTO spotDTO);

    void saveSpot(SpotDTO spot);
//    void deleteSpotById(Long id);
    void disableSpotById(Long id);
}
