package com.ryu.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AppError {
    DUPLICATED_USER("Thông tin người dùng đã tồn tại", HttpStatus.UNPROCESSABLE_ENTITY),
    LOGIN_INFO_INVALID("Thông tin đăng nhập không chính xác", HttpStatus.UNPROCESSABLE_ENTITY);

    private final String message;
    private final HttpStatus status;

    AppError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}