package com.ryu.common.models;

import lombok.Data;

@Data
public class BaseResponse<T> {
    T data;
    int statusCode;
    String message;

    public BaseResponse() {}

    public BaseResponse(T data, int status, String message) {
        this.data = data;
        this.statusCode = status;
        this.message = message;
    }

}
