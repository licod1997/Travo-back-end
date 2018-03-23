package com.travo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travo.model.Image;
import com.travo.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpotDTO implements Serializable {

    private Long id;
    private String spotName;
    private String description;
    private String x_location;
    private String y_location;
    private String z_location;
    private String address;
    private String provinceCity;
    private String district;
    private Boolean enable;
    private Integer commentCount;
    private Integer favouriteCount;
    private Long creatorId;
    private List<String> imgLink;
    private ArrayList<Long> commentIdArr;
    private ArrayList<Long> favouriteIdArr;


    public SpotDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getX_location() {
        return x_location;
    }

    public void setX_location(String x_location) {
        this.x_location = x_location;
    }

    public String getY_location() {
        return y_location;
    }

    public void setY_location(String y_location) {
        this.y_location = y_location;
    }

    public String getZ_location() {
        return z_location;
    }

    public void setZ_location(String z_location) {
        this.z_location = z_location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceCity() {
        return provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(Integer favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public ArrayList<Long> getCommentIdArr() {
        return commentIdArr;
    }

    public void setCommentIdArr(ArrayList<Long> commentIdArr) {
        this.commentIdArr = commentIdArr;
    }

    public ArrayList<Long> getFavouriteIdArr() {
        return favouriteIdArr;
    }

    public void setFavouriteIdArr(ArrayList<Long> favouriteIdArr) {
        this.favouriteIdArr = favouriteIdArr;
    }

    public List<String> getImgLink() {
        return imgLink;
    }

    public void setImgLink(List<String> imgLink) {
        this.imgLink = imgLink;
    }
}
