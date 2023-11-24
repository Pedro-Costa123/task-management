package com.personal.project.pedro.taskmanagement.controllers;

import com.personal.project.pedro.taskmanagement.entities.User;
import com.personal.project.pedro.taskmanagement.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getById")
    public User getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return "User created with id: " + userCreated.getUserId();
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "User deleted with id: " + id;
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        User userUpdated = userService.updateUser(user);
        return "User updated with id: " + userUpdated.getUserId();
    }
}
