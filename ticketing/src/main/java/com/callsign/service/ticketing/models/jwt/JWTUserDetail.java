package com.callsign.service.ticketing.models.jwt;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Setter
@Getter
@Slf4j
public class JWTUserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public JWTUserDetail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static JWTUserDetail build(SystemUser user) {

        return new JWTUserDetail(user.getUserEmail(),user.getPwd());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JWTUserDetail that = (JWTUserDetail) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
