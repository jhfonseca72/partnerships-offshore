package com.vivid.partnerships.interview.controllers;

import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.execptions.EventException;
import com.vivid.partnerships.interview.services.EventService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class EventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    private final EventService eventService;

    @GetMapping("/events")
    public List<Event> getEvents() {
        List<Event> events = this.eventService.getEvents();
        LOGGER.info("Returning {} events", events.size());
        return events;
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) throws EventException {
        LOGGER.info("Creating a new event {} ", event);
        return this.eventService.createEventWithNewVenue(event);
    }
}
