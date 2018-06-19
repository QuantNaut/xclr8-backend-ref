package com.xclr8.api.repository;

import com.xclr8.api.domain.UserDeviceTokenMapping;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the UserDeviceTokenMapping entity.
 */
@SuppressWarnings("unused")
public interface UserDeviceTokenMappingRepository extends MongoRepository<UserDeviceTokenMapping,String> {

    List<UserDeviceTokenMapping> findAllByUserId(String userId);

    UserDeviceTokenMapping findByUserIdAndDeviceToken(String userId, String deviceToken);
}
