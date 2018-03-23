package com.travo.dto;

import com.travo.model.Spot;
import com.travo.model.User;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asus on 3/23/2018.
 */
public class ImageDTO implements Serializable {
    private Long id;
    private String imageUrl;
    private Date createdTime;
    private Long userId;
    private Long spotId;

    public ImageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
    }
}
