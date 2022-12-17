package com.example.dnsproject.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String accessToken;
    private String responseCode;
    private String responseStatus;
}
