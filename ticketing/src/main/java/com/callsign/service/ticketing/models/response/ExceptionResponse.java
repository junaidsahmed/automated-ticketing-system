package com.callsign.service.ticketing.models.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Getter
@Setter
public class ExceptionResponse extends BasicResponse{
    private String errorMessage;
    public ExceptionResponse(boolean success, HttpStatus httpStatus, Object data) {
        super(success, httpStatus, data);
    }
}
