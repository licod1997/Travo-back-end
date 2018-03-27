package com.travo.service;

import com.travo.dto.PageDTO;
import com.travo.dto.PopularSpotDTO;
import com.travo.model.Spot;
import com.travo.model.User;
import com.travo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.*;

@Service
public class SuggestSpotServiceImpl implements SuggestSpotService {
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
}
