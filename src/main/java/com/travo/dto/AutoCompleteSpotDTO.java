package com.travo.dto;

import java.io.Serializable;

public class AutoCompleteSpotDTO implements Serializable{
    private long id;
    private String name;

    public AutoCompleteSpotDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AutoCompleteSpotDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
