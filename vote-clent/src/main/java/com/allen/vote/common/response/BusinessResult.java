package com.allen.vote.common.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BusinessResult<T> implements Serializable {

    private Integer status;

    private T data;

    private String message;

    private long timestamp;

    public BusinessResult() {
        this.timestamp = System.currentTimeMillis();
    }

    public BusinessResult(Integer status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }



    public static <T> BusinessResult<T> ok(T data){
        return new BusinessResult<>(200,data,"ok");
    }


    public static BusinessResult error(String message){
        return new BusinessResult(500,null,message);
    }


    public static BusinessResult error(int errorCode, String message){
        return new BusinessResult(errorCode,null,message);
    }

}
