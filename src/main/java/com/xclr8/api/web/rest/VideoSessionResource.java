package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.opentok.exception.OpenTokException;
import com.xclr8.api.domain.VideoSession;
import com.xclr8.api.dto.APIResponseDTO;
import com.xclr8.api.dto.APIResponseVideoSessionDTO;
import com.xclr8.api.service.VideoSessionService;
import com.xclr8.api.web.rest.util.HeaderUtil;

import com.xclr8.api.web.rest.util.VideoSessionCreationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VideoSession.
 */
@RestController
@RequestMapping("/api")
public class VideoSessionResource {

    private final Logger log = LoggerFactory.getLogger(VideoSessionResource.class);

    @Inject
    private VideoSessionService videoSessionService;

    /**
     * POST  /video-sessions : Create a new videoSession.
     *
     * @param videoSession the videoSession to create
     * @return the ResponseEntity with status 201 (Created) and with body the new videoSession, or with status 400 (Bad Request) if the videoSession has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/video-sessions")
    @Timed
    public ResponseEntity<APIResponseVideoSessionDTO> createVideoSession(@RequestBody VideoSession videoSession) throws URISyntaxException, OpenTokException {
        log.debug("REST request to save VideoSession : {}", videoSession);
        APIResponseVideoSessionDTO apiResponseVideoSessionDTO = new APIResponseVideoSessionDTO();
        if (videoSession.getId() != null) {
            apiResponseVideoSessionDTO.setStatus("FAILURE");
            apiResponseVideoSessionDTO.setCode("204");
            apiResponseVideoSessionDTO.setMessage("Id should be null");
            return ResponseEntity.ok(apiResponseVideoSessionDTO);
        }else {
            VideoSession videoSessionCreatedByUtil = VideoSessionCreationUtil.createSession();
            videoSession.setVideoSessionToken(videoSessionCreatedByUtil.getVideoSessionToken());
            videoSession.setVideoSessionId(videoSessionCreatedByUtil.getVideoSessionId());
            apiResponseVideoSessionDTO.setStatus("SUCCESS");
            apiResponseVideoSessionDTO.setCode("200");
            apiResponseVideoSessionDTO.setMessage("Session Created");
            VideoSession result = videoSessionService.saveAndDeleteAllInvalidTokens(videoSession);
            apiResponseVideoSessionDTO.setVideoSession(result);
            return ResponseEntity.created(new URI("/api/video-sessions/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("videoSession", result.getId().toString()))
                .body(apiResponseVideoSessionDTO);
        }
    }
    /**
     * GET  /video-sessions/:id : get the "id" videoSession.
     *
     * @param patientId the id of the videoSession to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the videoSession, or with status 404 (Not Found)
     */
    @GetMapping("/video-sessions/join-session/{patientId}")
    @Timed
    public ResponseEntity<APIResponseVideoSessionDTO> joinVideoSession(@PathVariable String patientId) {
        log.debug("REST request to get VideoSession : {}", patientId);
        APIResponseVideoSessionDTO apiResponseVideoSessionDTO = new APIResponseVideoSessionDTO();
        VideoSession videoSession = videoSessionService.findOneByPatientId(patientId);
        if(videoSession != null){
            apiResponseVideoSessionDTO.setVideoSession(videoSession);
            apiResponseVideoSessionDTO.setMessage("OK");
            apiResponseVideoSessionDTO.setCode("200");
            apiResponseVideoSessionDTO.setStatus("SUCCESS");
        }else{
            apiResponseVideoSessionDTO.setStatus("FAILURE");
            apiResponseVideoSessionDTO.setCode("204");
            apiResponseVideoSessionDTO.setMessage("Video Session does not exists");
        }
        return ResponseEntity.ok(apiResponseVideoSessionDTO);
    }


//    /**
//     * PUT  /video-sessions : Updates an existing videoSession.
//     *
//     * @param videoSession the videoSession to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated videoSession,
//     * or with status 400 (Bad Request) if the videoSession is not valid,
//     * or with status 500 (Internal Server Error) if the videoSession couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/video-sessions")
//    @Timed
//    public ResponseEntity<VideoSession> updateVideoSession(@RequestBody VideoSession videoSession) throws URISyntaxException {
//        log.debug("REST request to update VideoSession : {}", videoSession);
//        if (videoSession.getId() == null) {
//            return createVideoSession(videoSession);
//        }
//        VideoSession result = videoSessionService.save(videoSession);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert("videoSession", videoSession.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /video-sessions : get all the videoSessions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of videoSessions in body
     */
    @GetMapping("/video-sessions")
    @Timed
    public List<VideoSession> getAllVideoSessions() {
        log.debug("REST request to get all VideoSessions");
        return videoSessionService.findAll();
    }
//
//    /**
//     * GET  /video-sessions/:id : get the "id" videoSession.
//     *
//     * @param id the id of the videoSession to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the videoSession, or with status 404 (Not Found)
//     */
//    @GetMapping("/video-sessions/{id}")
//    @Timed
//    public ResponseEntity<VideoSession> getVideoSession(@PathVariable String id) {
//        log.debug("REST request to get VideoSession : {}", id);
//        VideoSession videoSession = videoSessionService.findOne(id);
//        return Optional.ofNullable(videoSession)
//            .map(result -> new ResponseEntity<>(
//                result,
//                HttpStatus.OK))
//            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    /**
//     * DELETE  /video-sessions/:id : delete the "id" videoSession.
//     *
//     * @param id the id of the videoSession to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
    @DeleteMapping("/video-sessions/{id}")
    @Timed
    public ResponseEntity<Void> deleteVideoSession(@PathVariable String id) {
        log.debug("REST request to delete VideoSession : {}", id);
        videoSessionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("videoSession", id.toString())).build();
    }

}
