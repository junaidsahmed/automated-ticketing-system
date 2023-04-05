package com.callsign.service.ticketing.models.jwt;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Getter
@Setter
public class JWTAuthReponse {

    private String email;
    private String token;
    private String type = "Bearer";

    public JWTAuthReponse(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
