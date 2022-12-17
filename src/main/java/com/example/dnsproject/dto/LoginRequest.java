package com.example.dnsproject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "UserName Cannot Be Null!!")
    private String userName;
    @NotEmpty(message = "UserName Cannot Be Null!!")
    private String password;
}
