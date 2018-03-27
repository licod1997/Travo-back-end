package com.travo.controller;

import com.travo.dto.LocationDTO;
import com.travo.dto.PageDTO;
import com.travo.dto.PopularSpotDTO;
import com.travo.model.User;
import com.travo.service.SuggestSpotService;
import com.travo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuggestSpotController {
    private SuggestSpotService suggestSpotService;
    private UserService userService;

    @Autowired
    public SuggestSpotController(SuggestSpotService suggestSpotService, UserService userService) {
        this.suggestSpotService = suggestSpotService;
        this.userService = userService;

    }

    @PostMapping("/popular-spot")
    public List<PopularSpotDTO> popularSpot(@RequestBody PageDTO pageDTO,
                                            Authentication auth) {
        User user = userService.findByUsername(auth.getName());
        return suggestSpotService.findPopularSpot(pageDTO, user);
    }

    @PostMapping("/near-me")
    public List<PopularSpotDTO> nearSpot(@RequestBody LocationDTO locationDTO,
                                         Authentication auth) {
        User user = userService.findByUsername(auth.getName());
        return suggestSpotService.findNearSpot(locationDTO, user);
    }
}
