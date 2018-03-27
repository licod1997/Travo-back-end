package com.travo.controller;

import com.travo.dto.HotSpotDTO;
import com.travo.dto.JsonResult;
import com.travo.dto.SpotDTO;
import com.travo.model.User;
import com.travo.service.FavoriteService;
import com.travo.service.SpotService;
import com.travo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;


@CrossOrigin@RestController
public class SpotController {

    private SpotService spotService;
    private UserService userService;
    private FavoriteService favoriteService;

    @Autowired
    public SpotController(SpotService spotService, UserService userService, FavoriteService favoriteService) {
        this.spotService = spotService;
        this.userService = userService;
        this.favoriteService= favoriteService;
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
    public ResponseEntity<SpotDTO> loadSpotDetail(@PathVariable("id") Long id,
                                                  Authentication auth){
        System.out.println("Load Spot Detail");
        User user = userService.findByUsername(auth.getName());
        return new ResponseEntity<>(spotService.findSpotDTOById(id, user), HttpStatus.OK);
    }

    @RequestMapping(value = "/spot/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SpotDTO> deleteSpot(@PathVariable("id") Long id,
                                              Authentication auth){
        User user = userService.findByUsername(auth.getName());
        SpotDTO spot = spotService.findSpotDTOById(id, user);
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

    @RequestMapping(value = "/spot/favorite", method = RequestMethod.GET)
    public String loadHotSpots(Authentication auth, @RequestParam(value = "spotId") Long spotId) {
        User user = userService.findByUsername(auth.getName());
        return spotService.favoriteSpot(spotId,user.getId());
    }
}
