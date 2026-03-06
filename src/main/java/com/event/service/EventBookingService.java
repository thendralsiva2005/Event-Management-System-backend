package com.event.service;

import com.event.dto.EventBookingRequest;
import com.event.dto.EventRequest;
import com.event.entity.EventBookingEntity;
import com.event.entity.EventEntity;
import com.event.entity.UserEntity;
import com.event.expection.RecordNotFoundException;
import com.event.mapper.EventBookingMapper;
import com.event.repository.EventBookingRepo;
import com.event.repository.EventRepo;
import com.event.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventBookingService {

    private final EventBookingRepo eventBookingRepo;
    private final UserRepo userRepo;
    private final EventRepo eventRepo;
    private final EventBookingMapper eventBookingMapper;

    public String bookEvent(EventBookingRequest eventBookingRequest) {
        EventDetails eventDetails = validateEvent(eventBookingRequest);
        EventBookingEntity eventBookingEntity = eventBookingMapper.toEntity(eventDetails.userEntity(),
                eventDetails.eventEntity());
        eventBookingRepo.save(eventBookingEntity);
        return "Event booked successfully";
    }

    private EventDetails validateEvent(EventBookingRequest eventBookingRequest) {
        Set<String> errors = new HashSet<>();

        UserEntity userEntity = userRepo.findById(eventBookingRequest.getUserId())
                .orElseGet(() -> {
                    errors.add("User not found with id: " + eventBookingRequest.getUserId());
                    return null;
                });

        EventEntity eventEntity = eventRepo.findById(eventBookingRequest.getEventId())
                .orElseGet(() -> {
                    errors.add("Event not found with id: " + eventBookingRequest.getEventId());
                    return null;
                });

        if (!errors.isEmpty()) {
            throw new RecordNotFoundException(errors + " ");
        }
        return new EventDetails(userEntity, eventEntity);
    }

    public List<EventRequest> getAllEventforUser(Integer userId) {
        List<EventBookingEntity> eventBookingEntities = eventBookingRepo.findByUserId(userId);

        return eventBookingEntities.stream()
                .map(booking -> {
                    EventRequest eventRequest = new EventRequest();
                    EventEntity event = booking.getEvent();

                    eventRequest.setBookingId(booking.getId());
                    eventRequest.setEventId(event.getId());
                    eventRequest.setTitle(event.getTitle());
                    eventRequest.setDescription(event.getDescription());
                    eventRequest.setLocation(event.getLocation());
                    eventRequest.setEventDate(event.getEventDate());
                    eventRequest.setCapacity(event.getCapacity());
                    eventRequest.setRegistrationDeadline(event.getRegistrationDeadline());
                    eventRequest.setUserId(userId);

                    return eventRequest;
                })
                .collect(Collectors.toList());
    }

    public String cancelBooking(Integer bookingId) {
        if (!eventBookingRepo.existsById(bookingId)) {
            throw new RecordNotFoundException("Booking not found with id: " + bookingId);
        }
        eventBookingRepo.deleteById(bookingId);
        return "Booking cancelled successfully";
    }

    public long countBookingsForUser(Integer userId) {
        return eventBookingRepo.countByUserId(userId);
    }

    private record EventDetails(UserEntity userEntity, EventEntity eventEntity) {
    }
}
