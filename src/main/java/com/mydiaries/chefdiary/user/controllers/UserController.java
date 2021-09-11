package com.mydiaries.chefdiary.user.controllers;

import com.mydiaries.chefdiary.user.User;
import com.mydiaries.chefdiary.user.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody @Valid User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @DeleteMapping("/users/{userId}")
    public User deleteUser(@PathVariable Long userId) {
        User userToDelete = getUser(userId);
        userRepository.delete(userToDelete);
        return userToDelete;
    }
}
