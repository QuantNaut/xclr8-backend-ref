package com.xclr8.api.service;

import com.xclr8.api.domain.VideoSession;
import com.xclr8.api.repository.VideoSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing VideoSession.
 */
@Service
public class VideoSessionService {

    private final Logger log = LoggerFactory.getLogger(VideoSessionService.class);

    @Inject
    private VideoSessionRepository videoSessionRepository;

    /**
     * Save a videoSession.
     *
     * @param videoSession the entity to save
     * @return the persisted entity
     */
    public VideoSession save(VideoSession videoSession) {
        log.debug("Request to save VideoSession : {}", videoSession);
        VideoSession result = videoSessionRepository.save(videoSession);
        return result;
    }

    /**
     * Save a videoSession.
     *
     * @param videoSession the entity to save
     * @return the persisted entity
     */
    public VideoSession saveAndDeleteAllInvalidTokens(VideoSession videoSession) {
        log.debug("Request to save VideoSession : {}", videoSession);
        List<VideoSession>  duplicateSessions = videoSessionRepository.findAllByPatientIdAndDoctorId(videoSession.getPatientId(),
            videoSession.getDoctorId());
        for(VideoSession duplicateSession: duplicateSessions){
            videoSessionRepository.delete(duplicateSession.getId());
        }
        VideoSession result = videoSessionRepository.save(videoSession);
        return result;
    }

    /**
     *  Get all the videoSessions.
     *
     *  @return the list of entities
     */
    public List<VideoSession> findAll() {
        log.debug("Request to get all VideoSessions");
        List<VideoSession> result = videoSessionRepository.findAll();

        return result;
    }

    /**
     *  Get one videoSession by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public VideoSession findOne(String id) {
        log.debug("Request to get VideoSession : {}", id);
        VideoSession videoSession = videoSessionRepository.findOne(id);
        return videoSession;
    }

    /**
     *  Get one videoSession by id.
     *
     *  @param patienId the id of the entity
     *  @return the entity
     */
    public VideoSession findOneByPatientId(String patienId) {
        log.debug("Request to get VideoSession : {}", patienId);
        VideoSession videoSession = videoSessionRepository.findByPatientIdOrderByIdDesc(patienId);
        return videoSession;
    }


    /**
     *  Delete the  videoSession by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete VideoSession : {}", id);
        videoSessionRepository.delete(id);
    }
}
