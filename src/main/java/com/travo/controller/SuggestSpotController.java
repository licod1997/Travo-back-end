package com.travo.controller;

import com.travo.dto.PopularSpotDTO;
import com.travo.service.SuggestSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuggestSpotController {
    private SuggestSpotService suggestSpotService;

    @Autowired
    public SuggestSpotController(SuggestSpotService suggestSpotService) {
        this.suggestSpotService = suggestSpotService;
    }

    @PostMapping("/popular-spot")
    public List<PopularSpotDTO> popularSpot(@RequestBody PopularSpotDTO popularSpotDTO) {
        return null;
    }
}
