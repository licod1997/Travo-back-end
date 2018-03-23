package com.travo.dto;

/**
 * Created by asus on 3/23/2018.
 */
public class JsonResult {
    private String status="true";
    private Object data;

    public JsonResult() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
