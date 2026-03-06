package com.event.repository;

import com.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(String title, String location);
}
