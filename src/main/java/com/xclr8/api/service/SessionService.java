package com.xclr8.api.service;

import com.xclr8.api.domain.Session;
import com.xclr8.api.domain.User;
import com.xclr8.api.domain.UserDeviceTokenMapping;
import com.xclr8.api.repository.SessionRepository;
import com.xclr8.api.repository.UserDeviceTokenMappingRepository;
import com.xclr8.api.service.util.XCLR8ConfigurationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Session.
 */
@Service
public class SessionService {

    private final Logger log = LoggerFactory.getLogger(SessionService.class);

    @Inject
    private SessionRepository sessionRepository;

    @Inject
    private UserDeviceTokenMappingRepository userDeviceTokenMappingRepository;

    @Autowired
    private AWSService awsService;

    @Inject
    private MailService mailService;

    @Inject
    private UserService userService;
    /*@Inject
    private AWSService awsService;*/

    /**
     * Save a session.
     *
     * @param session the entity to save
     * @return the persisted entity
     */
    public Session save(Session session) {
        log.debug("Request to save Session : {}", session);
        Session result = sessionRepository.save(session);
        return result;
    }

    /**
     * Get all the sessions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Session> findAll(Pageable pageable) {
        log.debug("Request to get all Sessions");
        Page<Session> result = sessionRepository.findAll(pageable);
        return result;
    }

    /**
     * Get all the sessions.
     *
     * @return the list of entities
     */
    public List<Session> findAll() {
        log.debug("Request to get all Sessions");
        List<Session> result = sessionRepository.findAll();
        return result;
    }

    /**
     * Get all the sessions.
     *
     * @return the list of entities
     */
    public List<Session> findAllActiveSessionsForPatient(String patientId) {
        log.debug("Request to get all Sessions");
        List<Session> result = sessionRepository.findAllByIsActiveIsTrueAndPatientId(patientId);
        return result;
    }

    /**
     * Get all the active sessions.
     *
     * @param  patientId patientID
     * @return the list of entities
     */
    public List<Session> findAllSessionsForPatient(String patientId) {
        log.debug("Request to get all Sessions");
        List<Session> result = sessionRepository.findAllByPatientId(patientId);
        return result;
    }


    /**
     *
     *
     * @return the list of entities
     */
    public Session saveAndDisableAllInvalidSessions(Session session) {
        log.debug("Request to get all Sessions");
        List<Session> results = sessionRepository.findAllByPatientIdAndDoctorId(session.getPatientId(), session.getDoctorId());
        for(Session sessionObj : results){
            sessionObj.setActive(false);
            save(sessionObj);
        }
        return sessionRepository.save(session);
    }

    /**
     * Get one session by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Session findOne(String id) {
        log.debug("Request to get Session : {}", id);
        Session session = sessionRepository.findOne(id);
        return session;
    }

    /**
     * Delete the  session by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Session : {}", id);
        sessionRepository.delete(id);
    }


    /**
     * Send push notification
     *
     * @param session
     *
     */
    public void sendPush(Session session) {
        User user = userService.getUserWithAuthorities(session.getPatientId());
        List<UserDeviceTokenMapping> userDeviceTokenMappings = userDeviceTokenMappingRepository.findAllByUserId(session.getPatientId());
        log.debug("{}",userDeviceTokenMappings.size());
        if (userDeviceTokenMappings.size()>0) {
            for(UserDeviceTokenMapping userDeviceTokenMapping: userDeviceTokenMappings){
                awsService.sendAndroidAppNotification(XCLR8ConfigurationConstants.AWS_SNS_SERVER_API_KEY, XCLR8ConfigurationConstants.AWS_SNS_APPLICATION_NAME, userDeviceTokenMapping.getDeviceToken(), session.getSessionInvitationMessage());
            }
        }else{
            if(user!=null)
                mailService.sendInvitationMail(user.getEmail());
        }
    }
}
