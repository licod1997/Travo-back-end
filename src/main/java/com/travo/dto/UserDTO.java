package com.travo.dto;

import com.travo.model.Spot;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 3/23/2018.
 */
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String imageUrl;
    private ArrayList<Long> createdSpotIdArr;
    private ArrayList<Long> diaryIdArr;
    private ArrayList<Long> imgIdArr;
    private ArrayList<Long> commentIdArr;
    private ArrayList<Long> roleIdArr;
    private ArrayList<Long> spotWishListId;
    private ArrayList<Long> spotFavoriteId;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<Long> getCreatedSpotIdArr() {
        return createdSpotIdArr;
    }

    public void setCreatedSpotIdArr(ArrayList<Long> createdSpotIdArr) {
        this.createdSpotIdArr = createdSpotIdArr;
    }

    public ArrayList<Long> getDiaryIdArr() {
        return diaryIdArr;
    }

    public void setDiaryIdArr(ArrayList<Long> diaryIdArr) {
        this.diaryIdArr = diaryIdArr;
    }

    public ArrayList<Long> getImgIdArr() {
        return imgIdArr;
    }

    public void setImgIdArr(ArrayList<Long> imgIdArr) {
        this.imgIdArr = imgIdArr;
    }

    public ArrayList<Long> getCommentIdArr() {
        return commentIdArr;
    }

    public void setCommentIdArr(ArrayList<Long> commentIdArr) {
        this.commentIdArr = commentIdArr;
    }

    public ArrayList<Long> getRoleIdArr() {
        return roleIdArr;
    }

    public void setRoleIdArr(ArrayList<Long> roleIdArr) {
        this.roleIdArr = roleIdArr;
    }

    public ArrayList<Long> getSpotWishListId() {
        return spotWishListId;
    }

    public void setSpotWishListId(ArrayList<Long> spotWishListId) {
        this.spotWishListId = spotWishListId;
    }

    public ArrayList<Long> getSpotFavoriteId() {
        return spotFavoriteId;
    }

    public void setSpotFavoriteId(ArrayList<Long> spotFavoriteId) {
        this.spotFavoriteId = spotFavoriteId;
    }
}
