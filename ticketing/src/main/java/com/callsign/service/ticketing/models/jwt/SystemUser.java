package com.callsign.service.ticketing.models.jwt;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Entity
@Table(name = "system_user")
@Setter
@Getter
public class SystemUser {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer Userid;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name="password")
    private String pwd;

    @Email(message = "enter a valid email")
    @NotNull
    @Column(name="user_email")
    private String userEmail;


    public SystemUser(String userName, String pwd, String userEmail) {
        this.userName = userName;
        this.pwd = pwd;
        this.userEmail = userEmail;
    }

    public SystemUser() {

    }
}
