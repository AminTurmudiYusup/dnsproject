package com.example.dnsproject.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private String statusCode;
    private String message;
    private Date date;
}
