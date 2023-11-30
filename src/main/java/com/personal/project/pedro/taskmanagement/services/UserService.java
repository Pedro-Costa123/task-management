package com.personal.project.pedro.taskmanagement.services;

import com.personal.project.pedro.taskmanagement.dao.TaskRepository;
import com.personal.project.pedro.taskmanagement.dao.UserRepository;
import com.personal.project.pedro.taskmanagement.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id + " to delete");
        }

        taskRepository.deleteByUserId(id);

        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        Long userId = user.getUserId();
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + userId + " to update");
        }
        return userRepository.save(user);
    }

}
