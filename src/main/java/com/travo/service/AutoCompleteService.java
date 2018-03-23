package com.travo.service;

import com.travo.dto.AutoCompleteSpotDTO;

import java.util.List;

public interface AutoCompleteService {
    List<AutoCompleteSpotDTO> searchSpot(String value);
}
