package com.example.telrostesttask.controllers;

import com.example.telrostesttask.models.Status;
import com.example.telrostesttask.models.User;
import com.example.telrostesttask.dao.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(@Autowired UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> viewAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User viewUser(@PathVariable("id") long id) {
        return userService.findById(id).get();
    }


    @PostMapping
    public Status addUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new Status(true,"user created successfully");
        } catch (Exception e) {
            return new Status(false, "can't create user");
        }
    }

    @PatchMapping
    public Status updateUser(@RequestBody User user) {
        if (userService.findById(user.getId()).isPresent()) {
            userService.save(user);
            return new Status(true, "change successfully");
        } else {
            return new Status(false, "there is no such user to update");
        }
    }


    @DeleteMapping("/{id}")
    public Status deleteUser(@PathVariable("id") long id) {
        if (userService.findById(id).isPresent()) {
            userService.delete(userService.findById(id).get());
            return new Status(true, "delete successfully");
        } else {
            return new Status(false, "there is no such user to delete");
        }
    }
}
