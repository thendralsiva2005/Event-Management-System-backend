package com.event.controller;

import com.event.dto.Credentials;
import com.event.dto.UserRequest;
import com.event.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRequest> createUser(@Valid @RequestBody UserRequest userRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody Credentials credentials) {

        return ResponseEntity.ok(userService.loginUser(credentials));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUserById(@PathVariable Integer id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public List<UserRequest> getAllUsers() {

        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id,
            @Valid @RequestBody UserRequest userRequest) {

        return userService.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {

        return userService.deleteUser(id);
    }
}