package com.example.userservice.dto.response;

public class RegisterResponseDto {

    private String username;
    private boolean success;

    public RegisterResponseDto() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
