package com.vivid.partnerships.interview.mappers;

import com.vivid.partnerships.interview.entities.Event;
import com.vivid.partnerships.interview.entities.Venue;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<Event> {

    @Nullable
    @Override
    public Event mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        final Event event = new Event();
        event.setId(resultSet.getInt("id"));
        event.setDate(resultSet.getDate("date"));
        event.setName(resultSet.getString("name"));

        final Venue venue = new Venue();
        venue.setId(resultSet.getInt("id_venue"));
        venue.setName(resultSet.getString("name_venue"));
        venue.setCity(resultSet.getString("city"));
        venue.setState(resultSet.getString("state"));

        event.setVenue(venue);
        return event;
    }
}
