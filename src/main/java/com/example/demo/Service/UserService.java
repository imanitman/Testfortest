package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Domain.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
    public User createNewUser(User user){
        return this.userRepository.save(user);
    }
}
