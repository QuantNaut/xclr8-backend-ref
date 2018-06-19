package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.UserDeviceTokenMapping;
import com.xclr8.api.dto.APIResponseUserDeviceTokenMapping;
import com.xclr8.api.service.UserDeviceTokenMappingService;
import com.xclr8.api.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.management.resources.agent;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserDeviceTokenMapping.
 */
@RestController
@RequestMapping("/api")
public class UserDeviceTokenMappingResource {

    private final Logger log = LoggerFactory.getLogger(UserDeviceTokenMappingResource.class);

    @Inject
    private UserDeviceTokenMappingService userDeviceTokenMappingService;

    /**
     * POST  /user-device-token-mappings : Create a new userDeviceTokenMapping.
     *
     * @param userDeviceTokenMapping the userDeviceTokenMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userDeviceTokenMapping, or with status 400 (Bad Request) if the userDeviceTokenMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-device-token-mappings")
    @Timed
    public APIResponseUserDeviceTokenMapping createUserDeviceTokenMapping(@RequestBody UserDeviceTokenMapping userDeviceTokenMapping) throws URISyntaxException {
        log.debug("REST request to save UserDeviceTokenMapping : {}", userDeviceTokenMapping);
        APIResponseUserDeviceTokenMapping apiResponseUserDeviceTokenMapping = new APIResponseUserDeviceTokenMapping();
        apiResponseUserDeviceTokenMapping.setCode("200");
        apiResponseUserDeviceTokenMapping.setMessage("OK");
        apiResponseUserDeviceTokenMapping.setStatus("SUCCESS");
        List<UserDeviceTokenMapping> userDeviceTokenMappings = userDeviceTokenMappingService.findUserByUserId(userDeviceTokenMapping.getUserId());
        for(UserDeviceTokenMapping duplicateUserDeviceTokenMapping : userDeviceTokenMappings){
            userDeviceTokenMappingService.delete(duplicateUserDeviceTokenMapping.getId());
        }
        UserDeviceTokenMapping result = userDeviceTokenMappingService.save(userDeviceTokenMapping);
        apiResponseUserDeviceTokenMapping.setUserDeviceTokenMapping(result);
        return apiResponseUserDeviceTokenMapping;
    }

//    /**
//     * DELETE delete the entry
//     *
//     * @param userLogin userEmail
//     * @param deviceToken deviceToken
//     * @return
//     */
//    @DeleteMapping("/user-device-token-mappings")
//    @Timed
//    public APIResponseUserDeviceTokenMapping deleteUserDeviceTokenMapping(@RequestParam String userLogin, @RequestParam String deviceToken){
//        log.debug("Rest request to delete UserDeviceTokenMapping");
//        APIResponseUserDeviceTokenMapping apiResponseUserDeviceTokenMapping = new APIResponseUserDeviceTokenMapping();
//        userDeviceTokenMappingService.findByUserLoginAndDeviceToken(userLogin, deviceToken);
//        apiResponseUserDeviceTokenMapping.setCode("200");
//        apiResponseUserDeviceTokenMapping.setMessage("OK");
//        apiResponseUserDeviceTokenMapping.setStatus("SUCCESS");
//        return  apiResponseUserDeviceTokenMapping;
//    }
}
