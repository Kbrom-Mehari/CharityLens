package com.kbrom.charity_lens_backend.exception;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ErrorResponse(int statusCode, String message, String details, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

}
