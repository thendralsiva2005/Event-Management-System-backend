package com.event.controller;

import com.event.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;


    @PostMapping("/createEvent/{userId}")
    public String createEvent(@PathVariable Integer userId){
        return eventService.createEvent(userId);
    }

}
