package com.travo.service;

import com.travo.model.Spot;
import com.travo.model.User;
import com.travo.repository.SpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by asus on 3/22/2018.
 */
@Service
public class FavoriteServiceImpl implements FavoriteService{

    @Override
    public Integer countFavoriteBySpot(Spot spot) {
        Set<User> userFavorites = spot.getUsersFavorite();
        return userFavorites.size();
    }

    @Override
    public ArrayList<Long> getFavoriteUserIdBySpot(Spot spot) {
        Set<User> userFavorites = spot.getUsersFavorite();
        Iterator<User> itr = userFavorites.iterator();
        ArrayList<Long> arrayFavoriteId = new ArrayList<>();
        while (itr.hasNext()) {
            arrayFavoriteId.add(itr.next().getId());
        }
        return arrayFavoriteId;
    }




}
