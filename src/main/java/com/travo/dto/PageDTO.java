package com.travo.dto;

import java.io.Serializable;

public class PageDTO implements Serializable{
    private int page;
    private int size;

    public PageDTO() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
