package com.callsign.service.ticketing.models.request;

import lombok.Data;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Data
public class SystemUserRegistrationRequest {

    private String userName;
    private String pwd;
    private String email;
}
