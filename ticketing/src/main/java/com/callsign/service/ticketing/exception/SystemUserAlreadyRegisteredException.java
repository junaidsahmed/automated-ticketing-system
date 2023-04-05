package com.callsign.service.ticketing.exception;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
public class SystemUserAlreadyRegisteredException extends RuntimeException{
    public SystemUserAlreadyRegisteredException(String errorMessage) {
        super(errorMessage);
    }
}
