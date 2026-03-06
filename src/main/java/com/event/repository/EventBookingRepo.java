package com.event.repository;

import com.event.entity.EventBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventBookingRepo extends JpaRepository<EventBookingEntity, Integer> {

    List<EventBookingEntity> findByUserId(Integer userId);

    long countByUserId(Integer userId);

    Optional<EventBookingEntity> findByUserIdAndEventId(Integer userId, Integer eventId);
}
