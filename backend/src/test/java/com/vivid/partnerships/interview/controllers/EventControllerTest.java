package com.vivid.partnerships.interview.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivid.partnerships.interview.TestUtil;
import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.entities.Venue;
import com.vivid.partnerships.interview.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EventController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EventService eventService;

    private static final String URL = "/events";

    /**
     * @param events
     * @throws Exception
     */
    @DisplayName("")
    @ParameterizedTest(name = "\"{0}\" should be return in the response")
    @MethodSource({"findAllEvents"})
    @SuppressWarnings("unchecked")
    public void shouldYieldEventList(List<Event> events) throws Exception {

        when(eventService.getEvents()).thenReturn(events);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL)).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        verify(eventService).getEvents();

        String json = result.getResponse().getContentAsString();

        Assertions.assertThat(json).isNotNull();
        Assertions.assertThat(json).isEqualTo(mapper.writeValueAsString(events));
    }

    /**
     * @param event
     * @throws Exception
     */
    @DisplayName("")
    @ParameterizedTest(name = "\"{0}\" should create a new event")
    @MethodSource({"createEvents"})
    @SuppressWarnings("unchecked")
    public void shouldCreateNewEvent(Event event) throws Exception {

        when(eventService.createEventWithNewVenue(any(Event.class))).thenReturn(event);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(event))).andReturn();

        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(eventService).createEventWithNewVenue(any(Event.class));

        String json = result.getResponse().getContentAsString();
        Event responseEvents = mapper.readValue(json, Event.class);

        Assertions.assertThat(responseEvents).isNotNull().isEqualToComparingFieldByField(event);
    }

    /**
     * @return
     */
    private static Stream<List<Event>> findAllEvents() {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        return Stream.of(Arrays.asList(firstEvent));
    }

    /**
     * @return
     */
    private static Stream<Event> createEvents() {
        Event firstEvent = TestUtil.createMockEvent("Event", "Chicago", "Chicago", "Illinois");
        return Stream.of(firstEvent);
    }
}
