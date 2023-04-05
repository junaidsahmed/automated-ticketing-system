package com.callsign.service.ticketing.enums;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
public enum TicketMessage {

    DELIVERY_FAILED("delivery time has passed and food is not being delivered"),
    DELIVERY_TIME_ESTIMATION_ISSUE("delivery estimated time is greater then the delivery expected time");

    private final String message;

    TicketMessage( String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
