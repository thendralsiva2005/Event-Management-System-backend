package com.event.controller;

import com.event.dto.User;
import com.event.entity.UserEntity;
import com.event.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping
    public String createUser(@RequestBody User user) {

        log.info("@Sh12 API request received: create user");

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password) {

        log.info("@Sh12 API request received: login user");

        return userService.loginUser(email, password);
    }


    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Integer id) {

        log.info("@Sh12 API request received: get user by id {}", id);

        return userService.getUserById(id);
    }


    @GetMapping
    public List<UserEntity> getAllUsers() {

        log.info("@Sh12 API request received: get all users");

        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id,
                             @RequestBody User user) {

        log.info("@Sh12 API request received: update user {}", id);

        return userService.updateUser(id, user);
    }


    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {

        log.info("@Sh12 API request received: delete user {}", id);

        return userService.deleteUser(id);
    }
}