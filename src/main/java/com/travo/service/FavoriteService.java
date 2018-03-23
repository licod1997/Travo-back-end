package com.travo.service;

import com.travo.model.Spot;

import java.util.ArrayList;

/**
 * Created by asus on 3/22/2018.
 */
public interface FavoriteService {
    Integer countFavoriteBySpot(Spot spot);
    ArrayList<Long> getFavoriteUserIdBySpot(Spot spot);
}
