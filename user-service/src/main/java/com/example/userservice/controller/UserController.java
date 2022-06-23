package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.service.AuthService;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(params = "username")
    public ResponseEntity<User> findByUsername(@RequestParam("username") String username){
        User user = userService.findByUsername(username);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

}
