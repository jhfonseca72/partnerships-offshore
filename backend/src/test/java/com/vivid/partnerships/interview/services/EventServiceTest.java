package com.vivid.partnerships.interview.services;

import com.vivid.partnerships.interview.TestUtil;
import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.execptions.EventException;
import com.vivid.partnerships.interview.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Slf4j
public class EventServiceTest {

    @InjectMocks
    private EventService service;

    @Mock
    private EventRepository repository;


    @Test
    @DisplayName("Must search all events")
    public void shouldReturnAllEvents() {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        when(this.repository.getEvents()).thenReturn(Arrays.asList(firstEvent));

        List<Event> events = this.service.getEvents();
        Assertions.assertThat(events).isNotNull().hasSize(1);
    }

    @Test
    @DisplayName("Must create a new event")
    public void shouldCreateNewEvent() throws EventException {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        when(this.repository.createEventWithNewVenue(any(Event.class))).thenReturn(Optional.of(firstEvent));

        Event createdEvent = this.service.createEventWithNewVenue(firstEvent);
        Assertions.assertThat(createdEvent.getId()).isNotNull().isEqualTo(firstEvent.getId());
    }

    @Test
    @DisplayName("Should fail if an event does not have a venue")
    public void shouldFailWhenEventDoesNotHaveVenue() {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        firstEvent.setVenue(null);
        when(this.repository.createEventWithNewVenue(any(Event.class))).thenReturn(Optional.of(firstEvent));

        Assertions.assertThatThrownBy(() -> this.service.createEventWithNewVenue(firstEvent))
                .hasMessage(EventException.EventMessage.VENUE_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("Should fail if a venue does not have name")
    public void shouldFailWhenVenueDoesNotHaveName() {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        firstEvent.getVenue().setName(null);

        when(this.repository.createEventWithNewVenue(any(Event.class))).thenReturn(Optional.of(firstEvent));

        Assertions.assertThatThrownBy(() -> this.service.createEventWithNewVenue(firstEvent))
                .hasMessage(EventException.EventMessage.VENUE_NAME_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("Should fail if a venue does not have city")
    public void shouldFailWhenVenueDoesNotHaveCity() {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        firstEvent.getVenue().setCity(null);

        when(this.repository.createEventWithNewVenue(any(Event.class))).thenReturn(Optional.of(firstEvent));

        Assertions.assertThatThrownBy(() -> this.service.createEventWithNewVenue(firstEvent))
                .hasMessage(EventException.EventMessage.VENUE_CITY_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("Should fail if a venue does not have state")
    public void shouldFailWhenVenueDoesNotHaveState() {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        firstEvent.getVenue().setState(null);

        when(this.repository.createEventWithNewVenue(any(Event.class))).thenReturn(Optional.of(firstEvent));

        Assertions.assertThatThrownBy(() -> this.service.createEventWithNewVenue(firstEvent))
                .hasMessage(EventException.EventMessage.VENUE_STATE_NOT_FOUND.getMessage());
    }
}
