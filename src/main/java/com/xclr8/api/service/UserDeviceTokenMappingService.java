package com.xclr8.api.service;

import com.xclr8.api.domain.UserDeviceTokenMapping;
import com.xclr8.api.repository.UserDeviceTokenMappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing UserDeviceTokenMapping.
 */
@Service
public class UserDeviceTokenMappingService {

    private final Logger log = LoggerFactory.getLogger(UserDeviceTokenMappingService.class);

    @Inject
    private UserDeviceTokenMappingRepository userDeviceTokenMappingRepository;

    /**
     * Save a userDeviceTokenMapping.
     *
     * @param userDeviceTokenMapping the entity to save
     * @return the persisted entity
     */
    public UserDeviceTokenMapping save(UserDeviceTokenMapping userDeviceTokenMapping) {
        log.debug("Request to save UserDeviceTokenMapping : {}", userDeviceTokenMapping);
        UserDeviceTokenMapping result = userDeviceTokenMappingRepository.save(userDeviceTokenMapping);
        return result;
    }

    /**
     *  Get all the userDeviceTokenMappings.
     *
     *  @return the list of entities
     */
    public List<UserDeviceTokenMapping> findAll() {
        log.debug("Request to get all UserDeviceTokenMappings");
        List<UserDeviceTokenMapping> result = userDeviceTokenMappingRepository.findAll();

        return result;
    }

    /**
     *  Get one userDeviceTokenMapping by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public UserDeviceTokenMapping findOne(String id) {
        log.debug("Request to get UserDeviceTokenMapping : {}", id);
        UserDeviceTokenMapping userDeviceTokenMapping = userDeviceTokenMappingRepository.findOne(id);
        return userDeviceTokenMapping;
    }

    /**
     *  Get one userDeviceTokenMapping by id.
     *
     *  @param userLogin email address of the user
     *  @return the entity
     */
    public List<UserDeviceTokenMapping> findUserByUserId(String userLogin) {
        log.debug("Request to get UserDeviceTokenMapping : {}", userLogin);
        List<UserDeviceTokenMapping> userDeviceTokenMappings = userDeviceTokenMappingRepository.findAllByUserId(userLogin);
        return userDeviceTokenMappings;
    }

    /**
     *  Delete the  userDeviceTokenMapping by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete UserDeviceTokenMapping : {}", id);
        userDeviceTokenMappingRepository.delete(id);
    }

    /**
     *  Get one userDeviceTokenMapping by id.
     *
     *  @param userId id of the user
     *  @return the entity
     */
    public UserDeviceTokenMapping findByUserLoginAndDeviceToken(String userId, String deviceToken) {
        log.debug("Request to get UserDeviceTokenMapping : {}", userId);
        UserDeviceTokenMapping userDeviceTokenMapping = userDeviceTokenMappingRepository.findByUserIdAndDeviceToken(userId, deviceToken);
        return userDeviceTokenMapping;
    }
}
