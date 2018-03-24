package com.travo.service;

import com.travo.dto.SpotDTO;
import com.travo.model.Spot;
import com.travo.repository.CommentRepository;
import com.travo.repository.ImageRepository;
import com.travo.repository.SpotRepository;
import com.travo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {

    private SpotRepository spotRepo;
    private CommentRepository commentRepo;
    private UserRepository userRepo;
    private ImageService imgService;
    private ImageRepository imgRepo;
    private FavoriteService favoriteService ;
    private CommentService commnentService;
    private UserService userService;

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

    public List<SpotDTO> findAllSpot() {
        boolean enable = true;
        List<Spot> lstSpot = spotRepo.findSpotByEnable(true);
//        return lstSpot;
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

//    @Override
//    public void deleteSpotById(Long id) {
//        spotRepo.deleteSpotById(id);
////        spotRepo.delete(id);
//    }
}
