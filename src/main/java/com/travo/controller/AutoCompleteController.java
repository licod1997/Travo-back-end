package com.travo.controller;

import com.travo.dto.AutoCompleteSpotDTO;
import com.travo.model.Spot;
import com.travo.service.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AutoCompleteController {

    private AutoCompleteService autoCompleteService;

    @Autowired
    public AutoCompleteController(AutoCompleteService autoCompleteService) {
        this.autoCompleteService = autoCompleteService;
    }

    @GetMapping("/auto-complete")
    public List<AutoCompleteSpotDTO> findAllObject(@RequestParam(name = "value", required = false) String value) {
        List<Spot> spotList = autoCompleteService.searchSpot(value);
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
