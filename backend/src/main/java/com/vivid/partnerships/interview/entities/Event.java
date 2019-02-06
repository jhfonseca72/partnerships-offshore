package com.vivid.partnerships.interview.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Event {
    private Integer id;
    private String name;
    private Venue venue;

    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date date;
}
