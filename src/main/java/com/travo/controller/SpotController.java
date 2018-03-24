package com.travo.controller;

import com.travo.dto.JsonResult;
import com.travo.dto.SpotDTO;
import com.travo.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@CrossOrigin@RestController
public class SpotController {

    private SpotService spotService;

    @Autowired
    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @RequestMapping(value = "/spot/loadAllSpots", method = RequestMethod.GET)
    public ResponseEntity<List<SpotDTO>> loadAllSpots() {
        List<SpotDTO> lstSpots = spotService.findAllSpot();

        if (lstSpots.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lstSpots, HttpStatus.OK);
    }

    @RequestMapping(value = "/spot/{id}", method = RequestMethod.GET)
    public ResponseEntity<SpotDTO> loadSpotDetail(@PathVariable("id") int id){
        Long spotId = Long.valueOf(id);
        return new ResponseEntity<>(spotService.findSpotDTOById(spotId), HttpStatus.OK);
    }

    @RequestMapping(value = "/spot/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SpotDTO> deleteSpot(@PathVariable("id") Long id){
        JsonResult jsonResult = new JsonResult();
        SpotDTO spot = spotService.findSpotDTOById(id);
        if (spot == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        spotService.disableSpotById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/spot/createSpot", method = RequestMethod.POST)
    public ResponseEntity<SpotDTO> createSpot(@RequestBody SpotDTO spot, UriComponentsBuilder ucBuilder ) {
//            if (spotService.isSpotExisted(spot)){
//                return new ResponseEntity( new CustomErrorType("Unable to create. Spot with id: "+spot.getId()+" already existed"),HttpStatus.CONFLICT);
//            }
//            spotService.saveSpot(spot);
        return null;
    }
}
