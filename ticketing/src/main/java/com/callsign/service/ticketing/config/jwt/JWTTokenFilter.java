package com.callsign.service.ticketing.config.jwt;


import com.callsign.service.ticketing.services.implementation.SystemUserService;
import com.callsign.service.ticketing.utils.jwt.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Slf4j
public class JWTTokenFilter extends OncePerRequestFilter {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private SystemUserService systemUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = parseJWT(request);
            if (jwt != null && jwtTokenUtil.validateJwtToken(jwt)) {
                String username = jwtTokenUtil.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = systemUserService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, new ArrayList<>());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        catch (Exception e){
            log.error("error occurred during user authentication: "+e);
        }

        filterChain.doFilter(request, response);
    }


    private String parseJWT(HttpServletRequest request) {
        String header = request.getHeader(HEADER_STRING);

        if (StringUtils.hasText(header) && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(7);
        }
        log.debug(HEADER_STRING +" header is not found or JWT token is not starting with "+ TOKEN_PREFIX);
        return null;
    }
}
