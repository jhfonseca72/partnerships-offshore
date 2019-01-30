package com.vivid.partnerships.interview.controllers;

import com.vivid.partnerships.interview.services.EventService;
import com.vivid.partnerships.interview.entities.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class EventController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

    private final EventService eventService;

    @Autowired
    public EventController(final EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        List<Event> events = this.eventService.getEvents();
        LOGGER.info("Returning {} events", events.size());
        return events;
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        LOGGER.info("Creating a new event {} ", event);
        return this.eventService.createEventWithNewVenue(event);
    }
}
