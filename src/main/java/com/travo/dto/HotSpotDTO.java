package com.travo.dto;

import java.io.Serializable;

/**
 * Created by asus on 3/25/2018.
 */
public class HotSpotDTO implements Serializable, Comparable {
    private Long id;
    private String imgUrl;
    private String address;
    private int favCount;
    private int commentCount;
    private boolean isFavorited;

    public HotSpotDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFavCount() {
        return favCount;
    }

    public void setFavCount(int favCount) {
        this.favCount = favCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isFavorited() {
        return isFavorited;
    }

    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }

    @Override
    public int compareTo(Object o) {
        HotSpotDTO dto = (HotSpotDTO) o;
        return this.getFavCount() - ((HotSpotDTO) o).getFavCount();
    }
}
