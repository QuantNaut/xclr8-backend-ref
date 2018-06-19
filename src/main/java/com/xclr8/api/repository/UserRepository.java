/**
 * UserRepository
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */
package com.xclr8.api.repository;

import com.xclr8.api.domain.User;
import com.xclr8.api.service.QueryDslPredicateExecutor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.ZonedDateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the User entity.
 */
public interface UserRepository extends MongoRepository<User, String>, QueryDslPredicateExecutor<User>{

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    Page<User> findAllUsersByUserRole(String userRole, Pageable pageable);

    List<User> findDistinctUserByFirstNameLikeOrLastNameLikeAllIgnoreCase(String firstName, String lastName);
}
