package com.TrungTinhBackend.edu_ai_backend.service;

import com.TrungTinhBackend.edu_ai_backend.model.User;
import com.TrungTinhBackend.edu_ai_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository ;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}
