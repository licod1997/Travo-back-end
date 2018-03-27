package com.travo.controller;

import com.travo.dto.AutoCompleteSpotDTO;
import com.travo.service.AutoCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return autoCompleteService.searchSpot(value);
    }

    @GetMapping("/search-result")
    public long findObject(@RequestParam(name = "value", required = false) String value) {
        return autoCompleteService.findSpot(value);
    }
}
