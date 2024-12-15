package com.healthify.reponse;

import java.time.LocalDateTime;

public class UserDefinedResponse<T> {

    private String status;
    private String message;
    private LocalDateTime timestamp;
    private T data;

    // Constructors
    public UserDefinedResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public UserDefinedResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Factory methods for convenience
    public static <T> UserDefinedResponse<T> success(String message, T data) {
        return new UserDefinedResponse<>("success", message, data);
    }

    public static <T> UserDefinedResponse<T> failure(String message, T data) {
        return new UserDefinedResponse<>("failure", message, data);
    }
}
