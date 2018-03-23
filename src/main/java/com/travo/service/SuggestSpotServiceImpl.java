package com.travo.service;

import com.travo.dto.PageDTO;
import com.travo.dto.PopularSpotDTO;
import com.travo.model.Spot;
import com.travo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestSpotServiceImpl implements SuggestSpotService {
    private SpotRepository spotRepository;

    @Autowired
    public SuggestSpotServiceImpl(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public List<PopularSpotDTO> findPopularSpot(PageDTO pageDTO) {
        Pageable pageable = new PageRequest(pageDTO.getPage() - 1, pageDTO.getSize(), Sort.Direction.DESC);

        List<Spot> spotList = spotRepository.findByEnable(pageable, true);
        List<PopularSpotDTO> popularSpotDTOList = new ArrayList<>();

        for (Spot spot : spotList) {
            PopularSpotDTO popularSpotDTO = new PopularSpotDTO();
            popularSpotDTO.setId(spot.getId());
            popularSpotDTO.setSpotName(spot.getSpotName());
            popularSpotDTO.setAdress(spot.getAddress());
        }
        return popularSpotDTOList;
    }
}
