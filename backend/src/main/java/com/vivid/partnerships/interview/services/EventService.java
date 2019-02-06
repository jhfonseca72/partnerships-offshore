package com.vivid.partnerships.interview.services;

import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.entities.Venue;
import com.vivid.partnerships.interview.execptions.EventException;
import com.vivid.partnerships.interview.mappers.EventRowMapper;
import com.vivid.partnerships.interview.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;


    /**
     * Get all events registered in database
     *
     * @return
     */
    public List<Event> getEvents() {
        return this.eventRepository.getEvents();
    }

    /**
     * Create a new event with their venue associated
     *
     * @param event {@link Event} object to register
     * @return {@link Event} object registered
     */
    public Event createEventWithNewVenue(final Event event) throws EventException {
        applyValidationRules(event);
        return this.eventRepository.createEventWithNewVenue(event)
                .orElseThrow(() -> EventException.EventMessage.EVENT_SAVE_ERROR.build());
    }

    /**
     * Verify all invariants and if no fulfill some rule must to throw a bussines exception
     */
    private void applyValidationRules(final Event event) throws EventException {

        if (StringUtils.isEmpty(event.getName())) {
            throw EventException.EventMessage.EVENT_NAME_NOT_FOUND.build();
        }

        if (Objects.isNull(event.getDate())) {
            throw EventException.EventMessage.EVENT_DATE_NOT_FOUND.build();
        }

        if (StringUtils.isEmpty(event.getVenue())) {
            throw EventException.EventMessage.VENUE_NOT_FOUND.build();
        }

        if (StringUtils.isEmpty(event.getVenue().getName())) {
            throw EventException.EventMessage.VENUE_NAME_NOT_FOUND.build();
        }

        if (StringUtils.isEmpty(event.getVenue().getCity())) {
            throw EventException.EventMessage.VENUE_CITY_NOT_FOUND.build();
        }

        if (StringUtils.isEmpty(event.getVenue().getState())) {
            throw EventException.EventMessage.VENUE_STATE_NOT_FOUND.build();
        }
    }
}
