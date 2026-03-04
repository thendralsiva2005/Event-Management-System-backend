package com.event.service;

import com.event.entity.EventEntity;
import com.event.expection.RecordNotFoundExpection;
import com.event.repository.EventRepo;
import com.event.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventRepo eventRepo;
    private final UserRepo userRepo;

    public String createEvent(Integer userId) {
        validateUser(userId);
        EventEntity eventEntity = new EventEntity();
        eventEntity
    }

    private void validateUser(Integer userId){
        boolean findUser =userRepo.existsById(userId);
        if(findUser){
            throw new RecordNotFoundExpection("user not found with id :"+userId);
        }
    }
}
