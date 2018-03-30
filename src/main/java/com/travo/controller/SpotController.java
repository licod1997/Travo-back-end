package com.travo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.travo.dto.ImageDTO;
import com.travo.dto.SpotDTO;
import com.travo.model.Image;
import com.travo.model.User;
import com.travo.service.FavoriteService;
import com.travo.service.ImageService;
import com.travo.service.SpotService;
import com.travo.service.UserService;

@CrossOrigin
@RestController
public class SpotController {

	private SpotService spotService;
	private UserService userService;
	private FavoriteService favoriteService;
	private ImageService imageService;
	@Autowired
	public SpotController(SpotService spotService, UserService userService, FavoriteService favoriteService) {
		this.spotService = spotService;
		this.userService = userService;
		this.favoriteService = favoriteService;
	}

	@RequestMapping(value = "/spot/loadAllSpots", method = RequestMethod.GET)
	public ResponseEntity<List<SpotDTO>> loadAllSpots() {
		List<SpotDTO> lstSpots = spotService.findAllSpot();

		if (lstSpots.isEmpty()) {
			return new ResponseEntity<>(lstSpots, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstSpots, HttpStatus.OK);
	}

	@RequestMapping(value = "/spot/{id}", method = RequestMethod.GET)
	public ResponseEntity<SpotDTO> loadSpotDetail(@PathVariable("id") Long id, Authentication auth) {
		System.out.println("Load Spot Detail");
		User user = userService.findByUsername(auth.getName());
		return new ResponseEntity<>(spotService.findSpotDTOById(id, user), HttpStatus.OK);
	}

	@RequestMapping(value = "/spot/disable/{id}", method = RequestMethod.GET)
	public ResponseEntity<SpotDTO> disableSpot(@PathVariable("id") Long id, Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		SpotDTO spot = spotService.findSpotDTOById(id, user);
		if (spot == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		spotService.disableSpotById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/spot/createSpot", method = RequestMethod.POST)
	public ResponseEntity<SpotDTO> createSpot(@RequestBody SpotDTO spot, UriComponentsBuilder ucBuilder,
			MultipartFile file, Authentication auth) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			byte[] bytes = file.getBytes();
			String timeStamp = spotService.getDateTime();
			String fileName = timeStamp + ".jpg";
			Path path = Paths.get(
					"D:\\Document\\eclipse-workspace\\Travo-back-end-master\\src\\main\\resources\\static\\images\\"
							+ fileName);
			Files.write(path, bytes);
			String location = spotService.getImgUrl(fileName);
			ImageDTO imageDTO= new ImageDTO();
			imageDTO.setImageUrl(location);
			imageDTO.setCreatedTime(date);
			
			Image image= imageService.addImage(imageDTO);
			
			User user = userService.findByUsername(auth.getName());		
			spot.setCreatorId(user.getId());
			spotService.saveSpot(spot ,image);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if (spotService.isSpotExisted(spot)){
		// return new ResponseEntity( new CustomErrorType("Unable to create. Spot with
		// id: "+spot.getId()+" already existed"),HttpStatus.CONFLICT);
		// }
		// spotService.saveSpot(spot);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/spot/favorite", method = RequestMethod.GET)
	public String loadHotSpots(Authentication auth, @RequestParam(value = "spotId") Long spotId) {
		User user = userService.findByUsername(auth.getName());
		return spotService.favoriteSpot(spotId, user.getId());
	}

    @RequestMapping(value = "/spot/changeStatusSpot/{id}", method = RequestMethod.GET)
    public ResponseEntity changeSpotStatus(@PathVariable("id") Long spotId
                                            , Authentication auth){
        User user = userService.findByUsername(auth.getName());
        SpotDTO spot = spotService.findSpotDTOById(spotId, user);
        if (spot == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        spotService.changeStatusSpot(spotId);
        return  new ResponseEntity(HttpStatus.OK);
    }
}
