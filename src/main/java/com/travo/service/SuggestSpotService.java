package com.travo.service;

import com.travo.dto.PageDTO;
import com.travo.dto.PopularSpotDTO;

import java.util.List;

public interface SuggestSpotService {
    List<PopularSpotDTO> findPopularSpot(PageDTO pageDTO);
}
