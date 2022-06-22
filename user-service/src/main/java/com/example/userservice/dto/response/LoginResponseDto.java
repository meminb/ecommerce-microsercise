package com.example.userservice.dto.response;

public class LoginResponseDto {

    private Long id;

    private String username;

    private boolean auth;

    public LoginResponseDto() {
    }

    public LoginResponseDto(Long id, String username, boolean auth) {
        this.id = id;
        this.username = username;
        this.auth = auth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }
}
