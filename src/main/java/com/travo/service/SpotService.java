package com.travo.service;

import com.travo.dto.HotSpotDTO;
import com.travo.dto.SpotDTO;
import com.travo.model.Spot;
import com.travo.model.User;

import java.util.ArrayList;
import java.util.List;

public interface SpotService {
    List<SpotDTO> findAllSpot();
    SpotDTO findSpotDTOById(Long Id, User user);
    boolean isSpotExisted(SpotDTO spotDTO);
    Spot findSpotById(Long id);
    void saveSpot(SpotDTO spot);
    void disableSpotById(Long id);
//    void remove(Long spotId, Long userId);
    void saveFavorite(Long spotId, Long userId);
    String favoriteSpot(Long spotId, Long userId);
}
