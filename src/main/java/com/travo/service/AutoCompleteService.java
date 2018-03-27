package com.travo.service;

import com.travo.dto.AutoCompleteSpotDTO;
import com.travo.dto.SpotDTO;

import java.util.List;

public interface AutoCompleteService {
    List<AutoCompleteSpotDTO> searchSpot(String value);
    long findSpot(String value);
}
