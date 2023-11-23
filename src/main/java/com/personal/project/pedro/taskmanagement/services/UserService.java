package com.personal.project.pedro.taskmanagement.services;

import com.personal.project.pedro.taskmanagement.dao.UserRepository;
import com.personal.project.pedro.taskmanagement.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
