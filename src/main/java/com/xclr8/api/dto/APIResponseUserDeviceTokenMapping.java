package com.xclr8.api.dto;

import com.xclr8.api.domain.UserDeviceTokenMapping;

/**
 * Created by shenju on 5/6/17.
 */
public class APIResponseUserDeviceTokenMapping extends APIResponseDTO {

    UserDeviceTokenMapping userDeviceTokenMapping;

    public UserDeviceTokenMapping getUserDeviceTokenMapping() {
        return userDeviceTokenMapping;
    }

    public void setUserDeviceTokenMapping(UserDeviceTokenMapping userDeviceTokenMapping) {
        this.userDeviceTokenMapping = userDeviceTokenMapping;
    }
}
