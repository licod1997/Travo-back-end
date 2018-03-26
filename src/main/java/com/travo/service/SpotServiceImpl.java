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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SpotServiceImpl implements SpotService {

    private SpotRepository spotRepo;
    private CommentRepository commentRepo;
    private UserRepository userRepo;
    private ImageService imgService;
    private ImageRepository imgRepo;
    private FavoriteService favoriteService;
    private CommentService commnentService;

    @Autowired
    public SpotServiceImpl(SpotRepository spotRepo, CommentRepository commentRepo, UserRepository userRepo, ImageRepository imgRepo
                         ,FavoriteService favoriteService, CommentService commnentService,ImageService imgService) {
        this.spotRepo = spotRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.imgRepo = imgRepo;
        this.favoriteService = favoriteService;
        this.commnentService = commnentService;
        this.imgService = imgService;
    }

    @Override
    public List<SpotDTO> findAllSpot() {
        boolean enable = true;
        List<Spot> lstSpot = spotRepo.findSpotByEnable(true);
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
    public SpotDTO findSpotDTOById(Long Id) {
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
        dto.setCommentIdArr(commnentService.getCommentsIdArray(spot));
        dto.setCreatorId(spot.getCreator().getId());
        dto.setFavouriteIdArr(favoriteService.getFavoriteUserIdBySpot(spot));
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
    public void saveSpot(SpotDTO spot) {

    }


    @Override
    public void disableSpotById(Long id) {
        Spot spot = spotRepo.findSpotById(id);
        spot.setEnable(false);
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
    public void favoriteSpot(Long spotId, Long userId) {

        Spot spot = findSpotById(spotId);
        Set<User>  setUsersFavoriteSpot  = spot.getUsersFavorite();
        boolean isFavorited = false;
        Iterator<User> userIterator = setUsersFavoriteSpot.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();
            System.out.println("User Id : "+user.getId());
            if (user.getId() == userId) {
                isFavorited = true;
            }
        }
        if (isFavorited) {
            remove(spotId,userId);
        } else {
           saveFavorite(spotId,userId);
        }
    }
}
