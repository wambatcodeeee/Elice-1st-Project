package com.example.firstproject.user.service;

import com.example.firstproject.user.entity.User;
import com.example.firstproject.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void signup(String userId, String password) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        userRepository.save(user);
    }

    public User Login(String userId, String password){
        User user = userRepository.findByUserId(userId);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

}
