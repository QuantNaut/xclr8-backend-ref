package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.opentok.exception.OpenTokException;
import com.xclr8.api.domain.Session;
import com.xclr8.api.domain.VideoSession;
import com.xclr8.api.dto.APIResponseSessionDTO;
import com.xclr8.api.service.SessionService;
import com.xclr8.api.service.util.RandomUtil;
import com.xclr8.api.web.rest.util.HeaderUtil;
import com.xclr8.api.web.rest.util.PaginationUtil;

import com.xclr8.api.web.rest.util.VideoSessionCreationUtil;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Session.
 */
@RestController
@RequestMapping("/api")
public class SessionResource {

    private final Logger log = LoggerFactory.getLogger(SessionResource.class);

    @Inject
    private SessionService sessionService;

    /**
     * POST  /sessions : Create a new session.
     *
     * @param session the session to create
     * @return the ResponseEntity with status 201 (Created) and with body the new session, or with status 400 (Bad Request) if the session has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sessions")
    @Timed
    public APIResponseSessionDTO createSession(@Valid @RequestBody Session session) throws URISyntaxException {
        log.debug("REST request to save Session : {}", session);
        APIResponseSessionDTO apiResponseSessionDTO = new APIResponseSessionDTO();
        if (session.getId() != null) {
            apiResponseSessionDTO.setCode("400");
            apiResponseSessionDTO.setStatus("FAILURE");
            apiResponseSessionDTO.setMessage("ID Exists");
            return apiResponseSessionDTO;
        }
        session.setStartDate(ZonedDateTime.now());
        Session result = sessionService.save(session);
        apiResponseSessionDTO.setCode("200");
        apiResponseSessionDTO.setStatus("SUCCESS");
        apiResponseSessionDTO.setMessage("OK");
        List<Session> sessions = new ArrayList<>();
        sessions.add(result);
        apiResponseSessionDTO.setSessions(sessions);
        return apiResponseSessionDTO;
    }

    /**
     * PUT  /sessions : Updates an existing session.
     *
     * @param session the session to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated session,
     * or with status 400 (Bad Request) if the session is not valid,
     * or with status 500 (Internal Server Error) if the session couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sessions")
    @Timed
    public APIResponseSessionDTO updateSession(@Valid @RequestBody Session session) throws URISyntaxException {
        log.debug("REST request to update Session : {}", session);
        APIResponseSessionDTO apiResponseSessionDTO = new APIResponseSessionDTO();
        if (session.getId() == null) {
            return createSession(session);
        }
        apiResponseSessionDTO.setCode("200");
        apiResponseSessionDTO.setStatus("SUCCESS");
        apiResponseSessionDTO.setMessage("OK");
        List<Session> sessions = new ArrayList<>();
        Session result = sessionService.save(session);
        sessions.add(result);
        apiResponseSessionDTO.setSessions(sessions);
        return apiResponseSessionDTO;

    /*    return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("session", session.getId().toString()))
            .body(result);*/
    }

    /**
     * GET  /sessions : get all the sessions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sessions in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/sessions")
    @Timed
    public APIResponseSessionDTO getAllSessions(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Sessions");
        List<Session> sessions = sessionService.findAll();
        APIResponseSessionDTO apiResponseSessionDTO = new APIResponseSessionDTO();
        apiResponseSessionDTO.setCode("200");
        apiResponseSessionDTO.setMessage("OK");
        apiResponseSessionDTO.setStatus("SUCCESS");
        apiResponseSessionDTO.setSessions(sessions);
        return  apiResponseSessionDTO;
        /*HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sessions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);*/
    }


    /**
     * GET  /sessions : get all the sessions.
     *
     * @param patientId
     * @return the ResponseEntity with status 200 (OK) and the list of sessions in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/sessions/patient/{patientId}")
    @Timed
    public APIResponseSessionDTO getAllSessionsForPatient(@PathVariable String patientId)
        throws URISyntaxException {
        log.debug("REST request to get all Sessions for a patient");
        List<Session> sessions = sessionService.findAllSessionsForPatient(patientId);
        APIResponseSessionDTO apiResponseSessionDTO = new APIResponseSessionDTO();
        apiResponseSessionDTO.setCode("200");
        apiResponseSessionDTO.setMessage("OK");
        apiResponseSessionDTO.setStatus("SUCCESS");
        apiResponseSessionDTO.setSessions(sessions);
        return  apiResponseSessionDTO;
        /*HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sessions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);*/
    }

    /**
     * GET  /sessions/:id : get the "id" session.
     *
     * @param id the id of the session to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the session, or with status 404 (Not Found)
     */
    @GetMapping("/sessions/{id}")
    @Timed
    public APIResponseSessionDTO getSession(@PathVariable String id) {
        log.debug("REST request to get Session : {}", id);
        APIResponseSessionDTO apiResponseSessionDTO = new APIResponseSessionDTO();
        Session session = sessionService.findOne(id);
        if(session != null){
            apiResponseSessionDTO.setStatus("SUCCESS");
            apiResponseSessionDTO.setMessage("OK");
            apiResponseSessionDTO.setCode("200");
            List<Session> sessions = new ArrayList<>();
            sessions.add(session);
            apiResponseSessionDTO.setSessions(sessions);
        }else{
            apiResponseSessionDTO.setStatus("FAILURE");
            apiResponseSessionDTO.setMessage("No Session Found");
            apiResponseSessionDTO.setCode("204");
        }
        return apiResponseSessionDTO;
    }

    /**
     * DELETE  /sessions/:id : delete the "id" session.
     *
     * @param id the id of the session to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sessions/{id}")
    @Timed
    public ResponseEntity<Void> deleteSession(@PathVariable String id) {
        log.debug("REST request to delete Session : {}", id);
        sessionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("session", id.toString())).build();
    }

    /**
     *
     * @param session
     * @return
     */
    @PostMapping("/sessions/invite")
    @Timed
    public APIResponseSessionDTO createSessionInvite(@RequestBody Session session) throws OpenTokException {
        APIResponseSessionDTO apiResponseSessionDTO = new APIResponseSessionDTO();
        session.setSessionChannel(RandomUtil.generateRandomChannelKey());
        VideoSession videoSession = VideoSessionCreationUtil.createSession();
        session.setVideoSessionId(videoSession.getVideoSessionId());
        session.setVideoSessionToken(videoSession.getVideoSessionToken());
        apiResponseSessionDTO.setCode("200");
        apiResponseSessionDTO.setStatus("SUCCESS");
        apiResponseSessionDTO.setMessage("OK");
        session.setActive(true);
        sessionService.sendPush(session);
        Session result = sessionService.saveAndDisableAllInvalidSessions(session);
        List<Session> sessions = new ArrayList<>();
        sessions.add(result);
        apiResponseSessionDTO.setSessions(sessions);
        return apiResponseSessionDTO;
    }

    /**
     *
     * @param patientId
     * @return
     */
    @GetMapping("/sessions/join")
    @Timed
    public APIResponseSessionDTO joinSession(@RequestParam String patientId){
        APIResponseSessionDTO apiResponseSessionDTO = new APIResponseSessionDTO();
        List<Session> sessions = sessionService.findAllActiveSessionsForPatient(patientId);
        if(sessions.size()>0) {
            apiResponseSessionDTO.setCode("200");
            apiResponseSessionDTO.setStatus("SUCCESS");
            apiResponseSessionDTO.setMessage("OK");
            apiResponseSessionDTO.setSessions(sessions);
        }else{
            apiResponseSessionDTO.setMessage("No Sessions found for the patient");
            apiResponseSessionDTO.setStatus("FAILURE");
            apiResponseSessionDTO.setCode("204");
        }
        return apiResponseSessionDTO;
    }

}
