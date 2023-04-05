package com.callsign.service.ticketing.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Getter
@Setter
public class BasicResponse<T> {
    private boolean success;
    private HttpStatus status;
    private T data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    public BasicResponse(boolean success,  HttpStatus httpStatus, T data)
    {
        this.success = success;
        this.status = httpStatus;
        this.data = data;

        this.dateTime=LocalDateTime.now();
    }
}
