package com.travo.service;

import com.travo.constant.AppConstant;
import com.travo.dto.ImageDTO;
import com.travo.model.Image;
import com.travo.model.Spot;
import com.travo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by asus on 3/22/2018.
 */
@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;


    @Autowired
    public ImageServiceImpl (ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ImageDTO> getListImagesBySpot(Spot spot) {
        List<Image> lstImages = imageRepository.findBySpot(spot);
        List<ImageDTO> result = new ArrayList<>();
        for (Image img:lstImages) {
            ImageDTO dto = new ImageDTO();
            dto.setId(img.getId());
            dto.setImageUrl(AppConstant.imgUrlPrefix+img.getImageUrl());
            dto.setCreatedTime(img.getCreatedTime());
            dto.setSpotId(img.getSpot().getId());
            dto.setUserId(img.getUser().getId());
            result.add(dto);
        }
        return result;
    }
    @Override
    public Image addImage(ImageDTO imageDTO) {
    	Image image= new Image();
    	image.setImageUrl(imageDTO.getImageUrl());
    	image.setCreatedTime(imageDTO.getCreatedTime());
    	return imageRepository.save(image);
    	
    }
    
    @Override
    public List<String> getListImageLinkBySpot(Spot spot) {
        System.out.println(spot.getId()+"spot Id");
        Image imgage = imageRepository.findById(spot.getId());
        List<Image> lstImage = imageRepository.findBySpot(spot);
//        Iterator<Image> itr = lstImage.iterator();
        List<String> lstImgLink = new ArrayList<>();
//        while(itr.hasNext()) {
//            lstImgLink.add(itr.next().getImageUrl());
//        }
        for (Image img: lstImage) {
            lstImgLink.add(AppConstant.imgUrlPrefix+img.getImageUrl());
        }
        return lstImgLink;
    }

}
