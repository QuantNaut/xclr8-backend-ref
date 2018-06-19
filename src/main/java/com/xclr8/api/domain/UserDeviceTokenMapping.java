package com.xclr8.api.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A UserDeviceTokenMapping.
 */

@Document(collection = "user_device_token_mapping")
public class UserDeviceTokenMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("device_token")
    private String deviceToken;

    @Field("user_id")
    private String userId;

    @Field("device_type")
    private String deviceType;

    @Field("created_date")
    private ZonedDateTime createdDate;

    @Field("updated_date")
    private ZonedDateTime updatedDate;

    @Field("is_active")
    private Boolean isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public UserDeviceTokenMapping deviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        return this;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public UserDeviceTokenMapping deviceType(String deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public UserDeviceTokenMapping createdDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public UserDeviceTokenMapping updatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public UserDeviceTokenMapping isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDeviceTokenMapping userDeviceTokenMapping = (UserDeviceTokenMapping) o;
        if (userDeviceTokenMapping.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, userDeviceTokenMapping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "UserDeviceTokenMapping{" +
            "id=" + id +
            ", deviceToken='" + deviceToken + "'" +
            ", userId='" + userId + "'" +
            ", deviceType='" + deviceType + "'" +
            ", createdDate='" + createdDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", isActive='" + isActive + "'" +
            '}';
    }
}
