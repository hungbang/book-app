package com.evizi.sampleapplication.service;

import com.evizi.sampleapplication.model.User;
import com.evizi.sampleapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User userPostDtoToUser) {
        userRepository.save(userPostDtoToUser);
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
