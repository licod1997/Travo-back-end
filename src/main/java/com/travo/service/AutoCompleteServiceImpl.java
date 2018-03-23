package com.travo.service;

import com.travo.model.Spot;
import com.travo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoCompleteServiceImpl implements AutoCompleteService{
    private SpotRepository spotRepository;

    @Autowired
    public AutoCompleteServiceImpl(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public List<Spot> searchSpot(String value) {
        return spotRepository.findTop5BySpotNameContaining(value);
    }
}
