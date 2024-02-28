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

    public boolean validation(String userId){
        return userRepository.existsByUserId(userId);
    }

    public String findPassword(String userId){
        User user = userRepository.findByUserId(userId);
        return user.getPassword();
    }

    public User findUserByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public void signup(User user) {
        user.setUserId(user.getUserId());
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    public User login(String userId, String password){
        User user = userRepository.findByUserId(userId);
        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

}
