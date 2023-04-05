package com.callsign.service.ticketing.exception;

import com.callsign.service.ticketing.models.response.BasicResponse;
import com.callsign.service.ticketing.models.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SystemUserAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleExceptions(SystemUserAlreadyRegisteredException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(false,HttpStatus.NOT_ACCEPTABLE,"");
        response.setErrorMessage("user already registered with this email");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        log.warn(exception.getMessage());
        return entity;
    }
}
