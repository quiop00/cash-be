package com.ryu.auth.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonTypeName("user")
@AllArgsConstructor
public class Registration {
    @NotBlank(message = "Username không được để trống")
    @Pattern(regexp = "[\\w\\d]{1,30}", message = "Tên hiển thị chỉ được bao gồm chữ cái, kí tự số hoặc gạch dưới, ít nhất 1 kí tự số")
    private String username;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 8, max = 32, message = "Mật khẩu phải từ 8-32 kí tự")
    private String password;

    private String referCode;
}
