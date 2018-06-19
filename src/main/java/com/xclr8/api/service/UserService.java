/**
 * UserService
 *
 * version :1.0
 *
 * Created on 8/2/17.
 *
 * Copyright 2017 XCLR8
 *
 */
package com.xclr8.api.service;

import com.xclr8.api.domain.Authority;
import com.xclr8.api.domain.User;
import com.xclr8.api.repository.AuthorityRepository;
import com.xclr8.api.repository.UserRepository;
import com.xclr8.api.security.AuthoritiesConstants;
import com.xclr8.api.security.SecurityUtils;
import com.xclr8.api.service.util.RandomUtil;
import com.xclr8.api.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import javax.inject.Inject;
import java.util.*;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AuthorityRepository authorityRepository;

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                userRepository.save(user);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
       log.debug("Reset user password for reset key {}", key);

       return userRepository.findOneByResetKey(key)
            .filter(user -> {
                ZonedDateTime oneDayAgo = ZonedDateTime.now().minusHours(24);
                return user.getResetDate().isAfter(oneDayAgo);
           })
           .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                userRepository.save(user);
                return user;
           });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmail(mail)
            .filter(User::getActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(ZonedDateTime.now());
                userRepository.save(user);
                return user;
            });
    }

    public User createUser(String login, String password, String firstName, String lastName, String email,
        String langKey) {

        User newUser = new User();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.PATIENT);
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createDoctor(String login, String password, String firstName, String lastName, String email,
                           String langKey) {

        User newUser = new User();
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.DOCTOR);
        newUser.setUserRole("ROLE_DOCTOR");
        Set<Authority> authorities = new HashSet<>();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setLogin(login);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setLangKey(langKey);
        // new user is active
        newUser.setActivated(true);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        authorities.add(authority);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User createUser(ManagedUserVM managedUserVM) {
        User user = new User();
        user.setLogin(managedUserVM.getLogin());
        user.setFirstName(managedUserVM.getFirstName());
        user.setLastName(managedUserVM.getLastName());
        user.setEmail(managedUserVM.getEmail());
        if (managedUserVM.getLangKey() == null) {
            user.setLangKey("en"); // default language
        } else {
            user.setLangKey(managedUserVM.getLangKey());
        }
        if (managedUserVM.getAuthorities() != null) {
            Set<Authority> authorities = new HashSet<>();
            managedUserVM.getAuthorities().forEach(
                authority -> authorities.add(authorityRepository.findOne(authority))
            );
            user.setAuthorities(authorities);
        }
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(ZonedDateTime.now());
        user.setActivated(true);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }
    public User createPatient(ManagedUserVM managedUserVM) {
        User user = new User();
        user.setUserRole("ROLE_PATIENT");
        Authority authority = authorityRepository.findOne(AuthoritiesConstants.PATIENT);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);;
        user.setLogin(managedUserVM.getLogin());
        user.setFirstName(managedUserVM.getFirstName());
        user.setLastName(managedUserVM.getLastName());
        user.setEmail(managedUserVM.getEmail());
        user.setAddress(managedUserVM.getAddress());
        user.setDob(managedUserVM.getDob());
        user.setHeight(managedUserVM.getHeight());
        user.setWeight(managedUserVM.getWeight());
        user.setGender(managedUserVM.getGender());
        user.setMob(managedUserVM.getMob());
        user.setFixedLine(managedUserVM.getFixedLine());
        user.setEmergencyContactPerson(managedUserVM.getEmergencyContactPerson());
        user.setEmergencyContactNumber(managedUserVM.getEmergencyContactNumber());
        if (managedUserVM.getLangKey() == null) {
            user.setLangKey("en"); // default language
        } else {
            user.setLangKey(managedUserVM.getLangKey());
        }
        String password = RandomUtil.generatePassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(ZonedDateTime.now());
        user.setActivated(true);
        user.setAuthorities(authorities);
        userRepository.save(user);
        user.setPassword(password);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    public void updateUser(String firstName, String lastName, String email, String langKey) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setLangKey(langKey);
            userRepository.save(user);
            log.debug("Changed Information for User: {}", user);
        });
    }

    public void updateUser(String id, String login, String firstName, String lastName, String email,
        boolean activated, String langKey, Set<String> authorities) {

        Optional.of(userRepository
            .findOne(id))
            .ifPresent(user -> {
                user.setLogin(login);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setActivated(activated);
                user.setLangKey(langKey);
                Set<Authority> managedAuthorities = user.getAuthorities();
                managedAuthorities.clear();
                authorities.forEach(
                    authority -> managedAuthorities.add(authorityRepository.findOne(authority))
                );
                userRepository.save(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    public void updatePatient(String id, String login, String firstName, String lastName, String email,
                           boolean activated, String langKey, Set<String> authorities, String address, String gender, String height,
                              ZonedDateTime dob, String weight, String mob, String fixedLine, String emergencyContactPerson,
                              String emergencyContactNumber) {

        Optional.of(userRepository
            .findOne(id))
            .ifPresent(user -> {
                user.setLogin(login);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setGender(gender);
                user.setHeight(height);
                user.setWeight(weight);
                user.setFixedLine(fixedLine);
                user.setEmergencyContactPerson(emergencyContactPerson);
                user.setEmergencyContactNumber(emergencyContactNumber);
                user.setDob(dob);
                user.setMob(mob);
                user.setAddress(address);
                user.setEmail(email);
                user.setActivated(activated);
                user.setLangKey(langKey);
                Set<Authority> managedAuthorities = user.getAuthorities();
                Authority authority = authorityRepository.findOne(AuthoritiesConstants.PATIENT);
                managedAuthorities.add(authority);
                user.setAuthorities(managedAuthorities);
                userRepository.save(user);
                log.debug("Changed Information for User: {}", user);
            });
    }

    public void deleteUser(String login) {
        userRepository.findOneByLogin(login).ifPresent(user -> {
            userRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public void changePassword(String password) {
        userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).ifPresent(user -> {
            String encryptedPassword = passwordEncoder.encode(password);
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            log.debug("Changed password for User: {}", user);
        });
    }

    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public User getUserWithAuthorities(String id) {
        User user = userRepository.findOne(id);
        return user;
    }

    public User getUserWithAuthorities() {
        Optional<User> optionalUser = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
        User user = null;
        if (optionalUser.isPresent()) {
          user = optionalUser.get();
         }
         return user;
    }


    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     * </p>
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        ZonedDateTime now = ZonedDateTime.now();
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
        }
    }
}
