package com.travo.service;

import com.travo.dto.LocationDTO;
import com.travo.dto.PageDTO;
import com.travo.dto.PopularSpotDTO;
import com.travo.model.User;

import java.util.List;

public interface SuggestSpotService {
    List<PopularSpotDTO> findPopularSpot(PageDTO pageDTO, User loggedUser);
    List<PopularSpotDTO> findNearSpot(LocationDTO locationDTO, User loggedUser);
}
