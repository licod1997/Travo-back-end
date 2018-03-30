package com.travo.service;

import com.travo.config.securitymodel.SecurityUser;
import com.travo.controller.LoginController;
import com.travo.dto.HotSpotDTO;
import com.travo.dto.SpotDTO;
import com.travo.model.Image;
import com.travo.model.Spot;
import com.travo.model.User;
import com.travo.repository.CommentRepository;
import com.travo.repository.ImageRepository;
import com.travo.repository.SpotRepository;
import com.travo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotRepository spotRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ImageService imgService;
    @Autowired
    private ImageRepository imgRepo;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private CommentService commnentService;


//    public SpotServiceImpl(SpotRepository spotRepo, CommentRepository commentRepo, UserRepository userRepo, ImageRepository imgRepo
//                         ,FavoriteService favoriteService, CommentService commnentService,ImageService imgService) {
//        this.spotRepo = spotRepo;
//        this.commentRepo = commentRepo;
//        this.userRepo = userRepo;
//        this.imgRepo = imgRepo;
//        this.favoriteService = favoriteService;
//        this.commnentService = commnentService;
//        this.imgService = imgService;
//    }

    @Override
    public List<SpotDTO> findAllSpot() {
        boolean enable = true;
        List<Spot> lstSpot = spotRepo.findAll();
        System.out.println("List Spot size: "+lstSpot.size());
        ArrayList<SpotDTO> result = new ArrayList<>();
        for (Spot spot : lstSpot) {
               SpotDTO dto = new SpotDTO();
               dto.setId(spot.getId());
               dto.setSpotName(spot.getSpotName());
               dto.setDescription(spot.getDescription());
               dto.setX_location(spot.getxLocation());
               dto.setY_location(spot.getyLocation());
               dto.setZ_location(spot.getzLocation());
               dto.setAddress(spot.getAddress());
               dto.setProvinceCity(spot.getProvinceCity());
               dto.setDistrict(spot.getDistrict());
               dto.setEnable(spot.getEnable());
               System.out.println(spot.getId()+" size : "+lstSpot.size());
               dto.setCommentCount(commentRepo.findAllBySpot(spot).size());
               dto.setFavouriteCount(favoriteService.countFavoriteBySpot(spot));
               dto.setImgLink(imgService.getListImageLinkBySpot(spot));
               dto.setCommentIdArr(commnentService.getCommentsIdArray(spot));
               dto.setCreatorId(spot.getCreator().getId());
               dto.setFavouriteIdArr(favoriteService.getFavoriteUserIdBySpot(spot));
               result.add(dto);
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

    @Override
    public SpotDTO findSpotDTOById(Long Id, User loggedUser) {
        Spot spot = spotRepo.findOne(Id);
        SpotDTO dto = new SpotDTO();
        dto.setId(spot.getId());
        dto.setSpotName(spot.getSpotName());
        dto.setDescription(spot.getDescription());
        dto.setX_location(spot.getxLocation());
        dto.setY_location(spot.getyLocation());
        dto.setZ_location(spot.getzLocation());
        dto.setAddress(spot.getAddress());
        dto.setProvinceCity(spot.getProvinceCity());
        dto.setDistrict(spot.getDistrict());
        dto.setEnable(spot.getEnable());
        dto.setCommentCount(commentRepo.findAllBySpot(spot).size());
        dto.setFavouriteCount(favoriteService.countFavoriteBySpot(spot));
        dto.setImgLink(imgService.getListImageLinkBySpot(spot));
//        dto.setCommentIdArr(commnentService.getCommentsIdArray(spot));
        dto.setCreatorId(spot.getCreator().getId());
        dto.setFavouriteIdArr(favoriteService.getFavoriteUserIdBySpot(spot));
        Set<User> setUsersFavorites = spot.getUsersFavorite();
        Iterator<User> itr = setUsersFavorites.iterator();
        dto.setFavorite(false);
        while (itr.hasNext()) {
            User user = itr.next();
            if (user.getId() == loggedUser.getId()) {
                dto.setFavorite(true);
            }
        }
        return dto;
    }

    @Override
    public boolean isSpotExisted(SpotDTO spot) {
        Spot spot1 = findSpotById(spot.getId());
        if (spot1 != null) {
            return false;
        }
        return true;
    }

    @Override
    public Spot findSpotById(Long id) {
        return spotRepo.findSpotById(id);
    }

    @Override
    public Spot saveSpot(SpotDTO spotDTO, Image image) {
    	Spot spot= new Spot();
    	User user= userRepo.findById(spotDTO.getCreatorId());
    	spot.setAddress(spotDTO.getAddress());
    	spot.setCreator(user);
    	spot.setDescription(spotDTO.getDescription());
    	spot.getImages().add(image);
    	return spotRepo.save(spot);
    }
    @Override
    public String getDateTime()
	{
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
	    return timeStamp;
	}

    @Override
    public void disableSpotById(Long id) {
        Spot spot = spotRepo.findSpotById(id);
        spot.setEnable(false);
        spotRepo.save(spot);
    }

    @Override
    public void changeStatusSpot(Long id) {
        Spot spot = spotRepo.findSpotById(id);
        if (spot.getEnable() == true) {
            spot.setEnable(false);
        } else {
            spot.setEnable(true);
        }
        spotRepo.save(spot);
    }


    @Transactional
    private void remove(Long spotId, Long userId) {
        System.out.println("Removing Favorite...with spotId: "+spotId+" userId: "+userId);
        Spot spot = spotRepo.findOne(spotId);
        User user = userRepo.findOne(userId);
        System.out.println(spot.getId());
        System.out.println(user.getId());
        System.out.println(spot.getUsersFavorite().remove(user));
    }

    @Override
//    @Transactional
    public void saveFavorite(Long spotId, Long userId) {
        System.out.println("Saving Favorite...with spotId: "+spotId+" userId: "+userId);
        Spot spot = spotRepo.findOne(spotId);
        User user = userRepo.findOne(userId);
        spot.getUsersFavorite().add(user);
    }

    @Override
    @Transactional
    public String favoriteSpot(Long spotId, Long userId) {
        String blackHeart = "fas fa-heart";
        String whiteHeart = "far fa-heart";
        Spot spot = findSpotById(spotId);
        Set<User>  setUsersFavoriteSpot  = spot.getUsersFavorite();
        boolean isFavorited = false;
        Iterator<User> userIterator = setUsersFavoriteSpot.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            if (user.getId() == userId) {
                isFavorited = true;
            }
        }
        if (isFavorited) {
            remove(spotId,userId);
            return whiteHeart;
        } else {
           saveFavorite(spotId,userId);
            return blackHeart;
        }
    }
    @Override
	public String getImgUrl(String fileName) {
		String location = null;		
		try {
			File file = new ClassPathResource("static/images/"+fileName).getFile();
			location= file.getAbsolutePath();
		} catch (IOException ex) {
			
			ex.printStackTrace();
		}  
		return location;
	}
}
