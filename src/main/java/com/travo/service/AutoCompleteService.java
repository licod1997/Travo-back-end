package com.travo.service;

import com.travo.model.Spot;

import java.util.List;

public interface AutoCompleteService {
    List<Spot> searchSpot(String value);
}
