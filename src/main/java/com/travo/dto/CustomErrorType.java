package com.travo.dto;

import java.io.Serializable;

/**
 * Created by asus on 3/22/2018.
 */
public class CustomErrorType implements Serializable{
    private String errorMessage;

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
