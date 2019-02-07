package com.vivid.partnerships.interview.repository;

import com.vivid.partnerships.interview.TestUtil;
import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.execptions.EventException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@JdbcTest
@ComponentScan
@Slf4j
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @Test
    public void shouldFindDefaultChicagoEvent() {
        List<Event> events = eventRepository.getEvents();
        Assertions.assertThat(events.get(0).getName())
                .isEqualTo("Chicago White Sox vs. Chicago Cubs");
    }

    @Test
    public void shouldFindAllEvents() {
        Event event = TestUtil.createMockEvent("Event", "Chicago", "Chicago",
                "Illinois");

        Optional<Event> createdEvent = this.eventRepository.createEventWithNewVenue(event);
        Assertions.assertThat(createdEvent).isNotEmpty().map(event1 -> event1.getId()).isNotNull();

        List<String> eventNames = eventRepository.getEvents().stream()
                .map(d -> d.getName()).collect(Collectors.toList());

        Assertions.assertThat(eventNames).isNotNull().hasSize(2)
                .contains("Event", "Chicago White Sox vs. Chicago Cubs");
    }
}


