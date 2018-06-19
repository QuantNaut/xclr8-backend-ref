/**
 * User
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */

package com.xclr8.api.domain;

import com.xclr8.api.config.Constants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.time.ZonedDateTime;

@Document(collection = "jhi_user")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    private String password;

    @Size(max = 50)
    @Field("first_name")
    private String firstName;

    @Size(max = 50)
    @Field("last_name")
    private String lastName;

    @Email
    @Size(max = 100)
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    @Field("lang_key")
    private String langKey;

    @Size(max=25)
    @Field("user_role")
    private String userRole;

    @Field("gender")
    private String gender;

    @Field("height")
    private String height;

    @Field("weight")
    private String weight;

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    @Field("mob")
    private String mob;

    @Field("dob")
    private ZonedDateTime dob = null;

    @Field("fixed_line")
    private String fixedLine;

    @Field("emergency_contact_person")
    private String emergencyContactPerson;

    @Field("emergency_contact_number")
    private String emergencyContactNumber;

    @Size(max=300)
    @Field("address")
    private String address;

    @Size(max = 20)
    @Field("activation_key")
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Field("reset_key")
    private String resetKey;

    @Field("reset_date")
    private ZonedDateTime resetDate = null;

    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    //Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = login.toLowerCase(Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFixedLine() { return fixedLine; }

    public void setFixedLine(String fixedLine) { this.fixedLine = fixedLine; }

    public String getEmergencyContactPerson() { return emergencyContactPerson; }

    public void setEmergencyContactPerson(String emergencyContactPerson) { this.emergencyContactPerson = emergencyContactPerson; }

    public String getEmergencyContactNumber() { return emergencyContactNumber; }

    public void setEmergencyContactNumber(String emergencyContactNumber) { this.emergencyContactNumber = emergencyContactNumber; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWeight() { return weight; }

    public void setWeight(String weight) { this.weight = weight; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address;}

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public ZonedDateTime getResetDate() {
       return resetDate;
    }

    public void setResetDate(ZonedDateTime resetDate) {
       this.resetDate = resetDate;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getUserRole() { return userRole; }

    public void setUserRole(String userRole) { this.userRole = userRole; }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getHeight() { return height; }

    public void setHeight(String height) { this.height = height; }

    public ZonedDateTime getDob() { return dob; }

    public void setDob(ZonedDateTime dob) { this.dob = dob; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!login.equals(user.login)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", activated='" + activated + '\'' +
            ", langKey='" + langKey + '\'' +
            ", gender='" + gender + '\'' +
            ", mob='" + mob + '\'' +
            ", height='" + height + '\'' +
            ", weight='" + weight + '\'' +
            ", dob='" + dob + '\'' +
            ", fixedLine='" + fixedLine + '\'' +
            ", emergencyContactPerson='" + emergencyContactPerson + '\'' +
            ", emergencyContactNumber='" + emergencyContactNumber + '\'' +
            ", user_role='" + userRole + '\'' +
            ", address='" + address + '\'' +
            ", activationKey='" + activationKey + '\'' +
            "}";
    }
}
