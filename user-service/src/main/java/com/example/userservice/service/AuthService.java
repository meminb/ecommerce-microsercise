package com.example.userservice.service;

import com.example.userservice.dto.request.LoginRequestDto;
import com.example.userservice.dto.request.RegisterRequestDto;
import com.example.userservice.dto.response.LoginResponseDto;
import com.example.userservice.dto.response.RegisterResponseDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDto authenticate(LoginRequestDto loginRequestDto) {
        String usernameFromRequest = loginRequestDto.getUsername();
        User user = userRepository.findByUsername(usernameFromRequest);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        if (user == null){
            loginResponseDto.setAuth(false);
            loginResponseDto.setUsername(null);
            loginResponseDto.setId(null);
        }else {
            loginResponseDto.setId(user.getId());
            loginResponseDto.setUsername(usernameFromRequest);
            loginResponseDto.setAuth(true);
        }

        return loginResponseDto;
    }

    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        String usernameFromRequest = registerRequestDto.getUsername();
        User user = userRepository.findByUsername(usernameFromRequest);
        RegisterResponseDto registerResponseDto = new RegisterResponseDto();
        if(user != null){
            registerResponseDto.setUsername("USERNAME ALREADY EXISTS");
            registerResponseDto.setSuccess(false);
            return registerResponseDto;
        }else {
            User newUser = new User(
                    registerRequestDto.getUsername(),
                    registerRequestDto.getPassword(),
                    registerRequestDto.getEmail(),
                    registerRequestDto.getAddress(),
                    registerRequestDto.getPhoneNumber()
            );
            userRepository.save(newUser);

            registerResponseDto.setUsername(registerRequestDto.getUsername());
            registerResponseDto.setSuccess(true);
            return registerResponseDto;
        }
    }
}
