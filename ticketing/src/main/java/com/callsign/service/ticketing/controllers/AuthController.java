package com.callsign.service.ticketing.controllers;


import com.callsign.service.ticketing.exception.SystemUserAlreadyRegisteredException;
import com.callsign.service.ticketing.models.jwt.JWTAuthReponse;
import com.callsign.service.ticketing.models.jwt.JWTAuthRequest;
import com.callsign.service.ticketing.models.jwt.JWTUserDetail;
import com.callsign.service.ticketing.models.request.SystemUserRegistrationRequest;
import com.callsign.service.ticketing.models.response.BasicResponse;
import com.callsign.service.ticketing.services.implementation.SystemUserService;
import com.callsign.service.ticketing.utils.jwt.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private SystemUserService saveSystemUser;

    private AuthenticationManager authenticationManager;

    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(SystemUserService saveSystemUser, AuthenticationManager authenticationManager, JWTTokenUtil jwtTokenUtil) {
        this.saveSystemUser = saveSystemUser;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/signup",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicResponse<String>> registerSystemUser(@RequestBody SystemUserRegistrationRequest userDto) throws SystemUserAlreadyRegisteredException {

        return ResponseEntity.ok(new BasicResponse<>(true,HttpStatus.OK,"user successfully registered with this email "+saveSystemUser.saveSystemUser(userDto).getUserEmail()));
    }
    @PostMapping(value = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthReponse> login(@RequestBody JWTAuthRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPwd()));
        log.debug(" check this "+authentication.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenUtil.generateJWTToken(authentication);

        JWTUserDetail userDetails = (JWTUserDetail) authentication.getPrincipal();

        return ResponseEntity.ok(new JWTAuthReponse(userDetails.getUsername(),jwtToken));
    }

}

