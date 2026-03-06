package com.event.service;

import com.event.dto.EventRequest;
import com.event.entity.EventEntity;
import com.event.entity.UserEntity;
import com.event.expection.RecordNotFoundException;
import com.event.mapper.EventMapper;
import com.event.repository.EventRepo;
import com.event.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
@Component
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventRepo eventRepo;
    private final UserRepo userRepo;
    private final EventMapper eventMapper;

    @Transactional
    public EventRequest createEvent(EventRequest eventRequest) {
        UserEntity userEntity = validateUser(eventRequest.getUserId());
        EventEntity eventEntity = eventMapper.toEventEntity(eventRequest);
        eventEntity.setCreatedBy(userEntity);
        eventRepo.save(eventEntity);
        return eventMapper.toDto(eventEntity);
    }

    @Transactional(readOnly = true)
    UserEntity validateUser(Integer userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RecordNotFoundException("user not found"));
    }

    @Transactional(readOnly = true)
    public List<EventRequest> getAllEvents() {
        return eventMapper.toDtoList(eventRepo.findAll());
    }

    @Transactional(readOnly = true)
    public EventRequest getEventById(Integer eventId) {
        EventEntity eventEntity = eventRepo.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event not found with id: " + eventId));
        return eventMapper.toDto(eventEntity);
    }

    @Transactional
    public EventRequest updateEvent(Integer eventId, EventRequest eventRequest) {
        EventEntity existingEvent = eventRepo.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event not found with id: " + eventId));

        existingEvent.setTitle(eventRequest.getTitle());
        existingEvent.setDescription(eventRequest.getDescription());
        existingEvent.setLocation(eventRequest.getLocation());
        existingEvent.setEventDate(eventRequest.getEventDate());
        existingEvent.setCapacity(eventRequest.getCapacity());
        existingEvent.setRegistrationDeadline(eventRequest.getRegistrationDeadline());
        eventRepo.save(existingEvent);

        return eventMapper.toDto(existingEvent);
    }

    @Transactional(readOnly = true)
    public List<EventRequest> searchEvents(String keyword) {
        List<EventEntity> results = eventRepo
                .findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(keyword, keyword);
        return eventMapper.toDtoList(results);
    }

    @Transactional
    public String deleteById(Integer eventId, Integer requestingUserId) {
        EventEntity eventEntity = eventRepo.findById(eventId)
                .orElseThrow(() -> new RecordNotFoundException("Event not found with id: " + eventId));

        if (!eventEntity.getCreatedBy().getId().equals(requestingUserId)) {
            throw new RuntimeException("You can only delete events that you created.");
        }

        eventRepo.deleteById(eventId);
        return "Event deleted successfully";
    }
}
