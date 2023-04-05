package com.callsign.service.ticketing.models.jwt;

import lombok.Data;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Data
public class JWTAuthRequest {
    private String email;
    private String pwd;

}
