package com.travo.dto;

import com.travo.model.User;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by asus on 3/23/2018.
 */
public class DiaryDTO implements Serializable {
    private Long id;
    private Date visitedDate;
    private Long userId;
    private Long spotId;

    public DiaryDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getVisitedDate() {
        return visitedDate;
    }

    public void setVisitedDate(Date visitedDate) {
        this.visitedDate = visitedDate;
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
