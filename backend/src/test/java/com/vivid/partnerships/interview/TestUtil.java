package com.vivid.partnerships.interview;

import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.entities.Venue;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

public final class TestUtil {

    private TestUtil() {
    }

    public static Event createMockEvent(String name, String venueCity, String venueName, String venueState) {
        Venue venue = new Venue();
        venue.setCity(venueCity);
        venue.setName(venueName);
        venue.setState(venueState);

        Event event = new Event();
        event.setDate(Date.from(Instant.now()));
        event.setName(name);
        event.setId(new Random().nextInt());
        event.setVenue(venue);

        return event;
    }
}
