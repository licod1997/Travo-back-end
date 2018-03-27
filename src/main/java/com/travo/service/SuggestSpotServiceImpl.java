package com.travo.service;

import com.travo.constant.AppConstant;
import com.travo.dto.LocationDTO;
import com.travo.dto.PageDTO;
import com.travo.dto.PopularSpotDTO;
import com.travo.model.Image;
import com.travo.model.Spot;
import com.travo.model.User;
import com.travo.repository.SpotRepository;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SuggestSpotServiceImpl implements SuggestSpotService {
    private static final double DETAULT_DISTANCE = 0.12;
    private SpotRepository spotRepository;

    @Autowired
    public SuggestSpotServiceImpl(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public List<PopularSpotDTO> findPopularSpot(PageDTO pageDTO, User loggedUser) {
        Pageable pageable = new PageRequest(pageDTO.getPage() - 1, pageDTO.getSize(), Sort.Direction.DESC, "id");

        List<Spot> spotList = spotRepository.findByEnable(pageable, true);
        List<PopularSpotDTO> popularSpotDTOList = new ArrayList<>();

        for (Spot spot : spotList) {
            PopularSpotDTO popularSpotDTO = new PopularSpotDTO();
            popularSpotDTO.setId(spot.getId());
            popularSpotDTO.setSpotName(spot.getSpotName());
            popularSpotDTO.setAdress(spot.getAddress());

            if (spot.getImages().iterator().hasNext()) {
                popularSpotDTO.setImageUrl(spot.getImages().iterator().next().getImageUrl());;
            } else {
                popularSpotDTO.setImageUrl("Unknown");
            }
            popularSpotDTO.setFavoriteCount(spot.getUsersFavorite().size());
            popularSpotDTO.setCommentCount(spot.getComments().size());
            Set<User> setUsersFavorites = spot.getUsersFavorite();
            Iterator<User> itr = setUsersFavorites.iterator();
            popularSpotDTO.setFavorite(false);
            while (itr.hasNext()) {
                User user = itr.next();
                if (user.getId() == loggedUser.getId()) {
                    popularSpotDTO.setFavorite(true);
                }
            }
            popularSpotDTOList.add(popularSpotDTO);
            Collections.sort(popularSpotDTOList, Collections.reverseOrder());

        }
        return popularSpotDTOList;
    }

    @Override
    public List<PopularSpotDTO> findNearSpot(LocationDTO locationDTO, User loggedUser) {
        List<Spot> spotList = spotRepository.findAll();
        List<PopularSpotDTO> nearSpotList = new ArrayList<>();

        for (Spot spot : spotList) {
            double curX = NumberUtils.toDouble(locationDTO.getLat());
            double curY = NumberUtils.toDouble(locationDTO.getLng());
            double spotX = NumberUtils.toDouble(spot.getxLocation());
            double spotY = NumberUtils.toDouble(spot.getyLocation());

            if (lengthOf2Positions(curX, curY, spotX, spotY) < DETAULT_DISTANCE) {
                PopularSpotDTO nearSpot = new PopularSpotDTO();
                nearSpot.setId(spot.getId());
                nearSpot.setSpotName(spot.getSpotName());
                nearSpot.setAdress(spot.getAddress());

                if (spot.getImages().iterator().hasNext()) {
                    nearSpot.setImageUrl(spot.getImages().iterator().next().getImageUrl());;
                } else {
                    nearSpot.setImageUrl("Unknown");
                }
                nearSpot.setFavoriteCount(spot.getUsersFavorite().size());
                nearSpot.setCommentCount(spot.getComments().size());
                Set<User> setUsersFavorites = spot.getUsersFavorite();
                Iterator<User> itr = setUsersFavorites.iterator();
                nearSpot.setFavorite(false);
                while (itr.hasNext()) {
                    User user = itr.next();
                    if (user.getId() == loggedUser.getId()) {
                        nearSpot.setFavorite(true);
                    }
                }
                nearSpotList.add(nearSpot);
                Collections.sort(nearSpotList, Collections.reverseOrder());
            }
        }

        return nearSpotList;
    }

    private double lengthOf2Positions(double lat1, double lng1, double lat2, double lng2) {
        return Math.sqrt(Math.pow((lat1 - lat2), 2) + Math.pow((lng1 - lng2), 2));
    }
}
