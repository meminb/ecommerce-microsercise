package com.example.userservice.service;

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

    public boolean authenticate(User user1) {
        User user = userRepository.findByUsername(user1.getUsername());
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(user1.getPassword());
    }


}
