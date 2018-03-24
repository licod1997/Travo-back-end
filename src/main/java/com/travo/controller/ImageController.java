package com.travo.controller;

import com.travo.dto.ImageDTO;
import com.travo.dto.SpotDTO;
import com.travo.model.Image;
import com.travo.model.Spot;
import com.travo.service.ImageService;
import com.travo.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asus on 3/24/2018.
 */
@RestController
@CrossOrigin
public class ImageController {
    private ImageService imageService;
    private SpotService spotService;

    @Autowired
    public ImageController(ImageService imageService, SpotService spotService) {
        this.imageService = imageService;
        this.spotService = spotService;
    }

    @RequestMapping(value = "/getListImages", method = RequestMethod.GET)
    public List<ImageDTO> getListImgagesBySpotId(@RequestParam(value = "spotId") Long spotId) {
        Spot spot = spotService.findSpotById(spotId);
        System.out.println("Loading Images List...");
        return imageService.getListImagesBySpot(spot);
    }
}
