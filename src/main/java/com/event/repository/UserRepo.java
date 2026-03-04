package com.event.repository;

import com.event.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    boolean existsByEmail( String email);

    Optional<UserEntity> findByEmail(String email);
}
