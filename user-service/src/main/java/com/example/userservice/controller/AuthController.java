package com.example.userservice.controller;

import com.example.userservice.dto.request.LoginRequestDto;
import com.example.userservice.dto.request.RegisterRequestDto;
import com.example.userservice.dto.response.LoginResponseDto;
import com.example.userservice.dto.response.RegisterResponseDto;
import com.example.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.authenticate(loginRequestDto);
    }

    @PostMapping("/register")
    public RegisterResponseDto register(@RequestBody RegisterRequestDto registerRequestDto){
        return authService.register(registerRequestDto);
    }
}
