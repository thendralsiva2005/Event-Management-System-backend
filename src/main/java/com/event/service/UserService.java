package com.event.service;

import com.event.dto.User;
import com.event.entity.UserEntity;
import com.event.expection.RecordNotFoundExpection;
import com.event.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepo userRepo;


    public String createUser(User user) {

        log.info("@Sh12 Creating user with email: {}", user.getEmail());

        if (userRepo.existsByEmail(user.getEmail())) {
            log.warn("@Sh12 User already exists with email: {}", user.getEmail());
            throw new RuntimeException("User already exists with this email");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        userRepo.save(userEntity);

        log.info("@Sh12 User successfully created with id: {}", userEntity.getId());

        return "User created successfully";
    }



    public String loginUser(String email, String password) {

        log.info("@Sh12 Login attempt for email: {}", email);

        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundExpection("User not found"));

        if (!user.getPassword().equals(password)) {
            log.warn("@Sh12 Invalid password for email: {}", email);
            throw new RuntimeException("Invalid password");
        }

        log.info("@Sh12 User logged in successfully: {}", email);

        return "Login successful";
    }


    public UserEntity getUserById(Integer id) {

        log.info("@Sh12 Fetching user with id: {}", id);

        return userRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundExpection("User not found"));
    }

    public List<UserEntity> getAllUsers() {

        log.info("@Sh12 Fetching all users");

        return userRepo.findAll();
    }


    public String updateUser(Integer id, User user) {

        log.info("@Sh12 Updating user with id: {}", id);

        UserEntity existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RecordNotFoundExpection("User not found"));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        userRepo.save(existingUser);

        log.info("@Sh12 User updated successfully with id: {}", id);

        return "User updated successfully";
    }


    public String deleteUser(Integer id) {

        log.info("@Sh12 Deleting user with id: {}", id);

        if (!userRepo.existsById(id)) {
            log.warn("@Sh12 User not found with id: {}", id);
            throw new RuntimeException("User not found");
        }

        userRepo.deleteById(id);

        log.info("@Sh12 User deleted successfully with id: {}", id);

        return "User deleted successfully";
    }
}