package com.event.controller;

import com.event.dto.EventRequest;
import com.event.service.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @PostMapping("/createEvent")
    public ResponseEntity<EventRequest> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventRequest));
    }
//
//    @GetMapping("/getAllEvents")
//    public ResponseEntity<List<EventRequest>> getAllEvent() {
//        return ResponseEntity.ok(eventService.getAllEvents());
//    }
//
//    @GetMapping("/{eventId}")
//    public ResponseEntity<EventRequest> getEventById(@PathVariable Integer eventId) {
//        return ResponseEntity.ok(eventService.getEventById(eventId));
//    }
//
//    @PutMapping("/{eventId}")
//    public ResponseEntity<EventRequest> updateEvent(@PathVariable Integer eventId,
//            @Valid @RequestBody EventRequest eventRequest) {
//        return ResponseEntity.ok(eventService.updateEvent(eventId, eventRequest));
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<EventRequest>> searchEvents(@RequestParam String keyword) {
//        return ResponseEntity.ok(eventService.searchEvents(keyword));
//    }

//    @DeleteMapping("/Delete/{eventId}")
//    public ResponseEntity<String> deleteEventById(@PathVariable Integer eventId,
//            @RequestParam Integer requestingUserId) {
//        return ResponseEntity.ok(eventService.deleteById(eventId, requestingUserId));
//    }
}
