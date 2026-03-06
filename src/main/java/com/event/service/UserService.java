package com.event.service;

import com.event.dto.Credentials;
import com.event.dto.UserRequest;
import com.event.entity.UserEntity;
import com.event.expection.RecordNotFoundException;
import com.event.mapper.UserMapper;
import com.event.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserRequest createUser(UserRequest userRequest) {

        if (userRepo.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("User already exists with this email");
        }

        UserEntity userEntity = userMapper.toEntity(userRequest);

        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRepo.save(userEntity);

        return userMapper.toDto(userEntity);
    }

    public String loginUser(Credentials credentials) {

        UserEntity user = userRepo.findByEmail(credentials.getEmail())
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        if (!passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return String.valueOf(user.getId());
    }

    public UserRequest getUserById(Integer id) {

        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        return userMapper.toDto(userEntity);
    }

    public List<UserRequest> getAllUsers() {
        return userMapper.toDtoList(userRepo.findAll());
    }

    public String updateUser(Integer id, UserRequest userRequest) {

        UserEntity existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User not found"));

        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());

        existingUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userRepo.save(existingUser);

        return "User updated successfully";
    }

    public String deleteUser(Integer id) {

        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepo.deleteById(id);

        return "User deleted successfully";
    }
}