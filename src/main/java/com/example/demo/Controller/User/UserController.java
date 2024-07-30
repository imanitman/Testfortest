package com.example.demo.Controller.User;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.User;
import com.example.demo.Service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    private PasswordEncoder passwordEncoder;
   
    private final UserService userService;

    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }  
    
    @PostMapping("/users")
    public User createUser (@RequestBody User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userService.createNewUser(user);
    }
    
}
