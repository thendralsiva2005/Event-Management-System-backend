package com.event.controller;

import com.event.dto.EventBookingRequest;
import com.event.dto.EventRequest;
import com.event.service.EventBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookingEvents")
@RequiredArgsConstructor
public class EventBookingController {

    private final EventBookingService eventBookingService;

    @PostMapping("/addEvents")
    public ResponseEntity<String> addEvents(@Valid @RequestBody EventBookingRequest eventBookingRequest) {
        return ResponseEntity.ok(eventBookingService.bookEvent(eventBookingRequest));
    }

    @GetMapping("/{userId}/bookedEvents")
    public ResponseEntity<List<EventRequest>> getAllEvents(@PathVariable Integer userId) {
        return ResponseEntity.ok(eventBookingService.getAllEventforUser(userId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer bookingId) {
        return ResponseEntity.ok(eventBookingService.cancelBooking(bookingId));
    }

    @GetMapping("/{userId}/count")
    public ResponseEntity<Long> getBookingCount(@PathVariable Integer userId) {
        return ResponseEntity.ok(eventBookingService.countBookingsForUser(userId));
    }
}
