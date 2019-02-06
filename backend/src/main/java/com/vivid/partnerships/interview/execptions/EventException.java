package com.vivid.partnerships.interview.execptions;

public class EventException extends Exception {

    public enum EventMessage {

        EVENT_NAME_NOT_FOUND("Must enter the name for the event"),
        EVENT_DATE_NOT_FOUND("Must enter a date for the event"),
        VENUE_NOT_FOUND("There is not no one celebrity"),
        VENUE_NAME_NOT_FOUND("Must enter the name of the venue"),
        VENUE_CITY_NOT_FOUND("Must enter the city of the venue"),
        VENUE_STATE_NOT_FOUND("Must enter the name of the venue"),
        EVENT_SAVE_ERROR("It was not possible to save the event, please try again later");

        private String message;

        EventMessage(String message) {
            this.message = message;
        }

        public EventException build() {
            return new EventException(this.message);
        }

        public String getMessage() {
            return message;
        }
    }

    private EventException(String message) {
        super(message);
    }
}
