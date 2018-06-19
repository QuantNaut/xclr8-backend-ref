/**
 * UserDTO
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */
package com.xclr8.api.dto;

import com.xclr8.api.config.Constants;

import com.xclr8.api.domain.Authority;
import com.xclr8.api.domain.User;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    @Size(max = 250)
    private String address;

    @Size(max = 10)
    private String gender;

    @Size(max = 10)
    private String height;

    @Size(max = 15)
    private String mob;

    @Size(max = 15)
    private String fixedLine;

    @Size(max = 30)

    private String emergencyContactPerson;

    @Size(max = 15)
    private String emergencyContactNumber;

    @Size(max = 10)
    private String weight;

    private ZonedDateTime dob = null;

    private Set<String> authorities;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), user.getFirstName(), user.getLastName(),
            user.getEmail(), user.getActivated(), user.getLangKey(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()), user.getAddress(), user.getGender(), user.getHeight(), user.getDob(), user.getWeight(),
            user.getMob(), user.getFixedLine(), user.getEmergencyContactPerson(), user.getEmergencyContactNumber());
    }


    public UserDTO(String login, String firstName, String lastName,
                   String email, boolean activated, String langKey, Set<String> authorities, String address, String gender, String height,
                   ZonedDateTime dob, String weight, String mob, String fixedLine,  String emergencyContactPerson,
                   String emergencyContactNumber) {

        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
        this.gender = gender;
        this.height = height;
        this.dob= dob;
        this.weight = weight;
        this.mob = mob;
        this.fixedLine= fixedLine;
        this.emergencyContactPerson = emergencyContactPerson;
        this.emergencyContactNumber = emergencyContactNumber;
    }
    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }



    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public ZonedDateTime getDob() {
        return dob;
    }

    public void setDob(ZonedDateTime dob) {
        this.dob = dob;
    }

    public String getWeight() { return weight; }

    public void setWeight(String weight) { this.weight = weight; }

    public String getFixedLine() { return fixedLine; }

    public void setFixedLine(String fixedLine) { this.fixedLine = fixedLine; }

    public String getEmergencyContactPerson() { return emergencyContactPerson; }

    public void setEmergencyContactPerson(String emergencyContactPerson) { this.emergencyContactPerson = emergencyContactPerson; }

    public String getEmergencyContactNumber() { return emergencyContactNumber; }

    public void setEmergencyContactNumber(String emergencyContactNumber) { this.emergencyContactNumber = emergencyContactNumber; }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", gender='" + gender + '\'' +
            ", height='" + height + '\'' +
            ", weight='" + weight + '\'' +
            ", mob='" + mob + '\'' +
            ", dob='" + dob + '\'' +
            ", fixedLine='" + fixedLine + '\'' +
            ", emergencyContactPerson='" + emergencyContactPerson + '\'' +
            ", emergencyContactNumber='" + emergencyContactNumber + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", authorities=" + authorities +
            "}";
    }
}
