package com.travo.service;

import com.travo.dto.AutoCompleteSpotDTO;
import com.travo.model.Spot;
import com.travo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoCompleteServiceImpl implements AutoCompleteService{
    private SpotRepository spotRepository;

    @Autowired
    public AutoCompleteServiceImpl(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public List<AutoCompleteSpotDTO> searchSpot(String value) {
        List<Spot> spotList = spotRepository.findTop5BySpotNameContaining(value);

        List<AutoCompleteSpotDTO> autoCompleteSpotDTOList = new ArrayList<>();

        for (Spot spot : spotList) {
            AutoCompleteSpotDTO autoCompleteSpotDTO = new AutoCompleteSpotDTO();
            autoCompleteSpotDTO.setId(spot.getId());
            autoCompleteSpotDTO.setName(spot.getSpotName());

            autoCompleteSpotDTOList.add(autoCompleteSpotDTO);
        }

        return autoCompleteSpotDTOList;
    }
}
