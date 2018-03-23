package com.travo.service;

import com.travo.model.Image;
import com.travo.model.Spot;

import java.util.List;
import java.util.Set;

/**
 * Created by asus on 3/22/2018.
 */
public interface ImageService {

    List<String> getListImageLinkBySpot(Spot spot);

    Set<Image> getListImagesBySpot(Spot spot);
}
