package com.vivid.partnerships.interview.repository;

import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.entities.Venue;
import com.vivid.partnerships.interview.mappers.EventRowMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EventRepository {


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventRepository.class);
    private static final String SELECT_EVENTS_WITH_VENUE = "SELECT e.id, e.name, e.date, v.id id_venue, v.name name_venue, v.city, v.state FROM events e JOIN venue v ON e.venue = v.id";
    private static final String SELECT_EVENT_BY_ID = "SELECT e.id, e.name, e.date, v.id id_venue, v.name name_venue, v.city, v.state FROM events e JOIN venue v ON e.venue = v.id WHERE e.id = ?";
    private static final String INSERT_VENUE = "INSERT INTO venue(name, city, state) VALUES (:name, :city, :state)";
    private static final String INSERT_EVENT = "INSERT INTO events(name, date, venue) VALUES (:name, :date, :venue)";
    private static final int RECORDS_NOT_FOUND = 0;

    /**
     * Get all events registered in database
     *
     * @return
     */
    public List<Event> getEvents() {
        return jdbcTemplate.query(SELECT_EVENTS_WITH_VENUE, new EventRowMapper());
    }

    /**
     * Create a new event with their venue associated
     *
     * @param event {@link Event} object to register
     * @return {@link Event} object registered
     */
    public Optional<Event> createEventWithNewVenue(final Event event) {
        return this.createVenue(event.getVenue()).map(venue -> {
            event.setVenue(venue);
            return this.createEvent(event);
        }).orElse(Optional.empty());
    }

    /**
     * Create a new event
     *
     * @param event {@link Event}
     * @return {@link Optional}
     */
    private Optional<Event> createEvent(final Event event) {

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", event.getName())
                .addValue("date", event.getDate())
                .addValue("venue", event.getVenue().getId());

        namedParameterJdbcTemplate.update(INSERT_EVENT, parameters, holder);
        event.setId(holder.getKey().intValue());

        return event.getId() <= RECORDS_NOT_FOUND ? Optional.empty() :
                Optional.of(this.findEventById(event.getId()));
    }

    /**
     * Create a new Venue
     *
     * @param venue
     * @return
     */
    private Optional<Venue> createVenue(Venue venue) {

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", venue.getName())
                .addValue("city", venue.getCity())
                .addValue("state", venue.getState());

        namedParameterJdbcTemplate.update(INSERT_VENUE, parameters, holder);
        venue.setId(holder.getKey().intValue());

        return venue.getId() <= RECORDS_NOT_FOUND ? Optional.empty() : Optional.of(venue);
    }


    /**
     * Find an event by id
     *
     * @param idEvent {@link Integer} event's id
     * @return {@link Event} event founded in the database applying the filter by id
     */
    private Event findEventById(Integer idEvent) {
        return jdbcTemplate.queryForObject(SELECT_EVENT_BY_ID,
                new Object[]{idEvent}, new EventRowMapper());
    }
}
