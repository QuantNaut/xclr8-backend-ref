package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.Notes;
import com.xclr8.api.dto.APIResponseNotesDTO;
import com.xclr8.api.service.NotesService;
import com.xclr8.api.web.rest.util.HeaderUtil;
import com.xclr8.api.dto.NotesDTO;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Notes.
 */
@RestController
@RequestMapping("/api")
public class NotesResource {

    private final Logger log = LoggerFactory.getLogger(NotesResource.class);

    @Inject
    private NotesService notesService;

    /**
     * POST  /notes : Create a new notes.
     *
     * @param notesDTO the notesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notesDTO, or with status 400 (Bad Request) if the notes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notes")
    @Timed
    public APIResponseNotesDTO createNotes(@RequestBody NotesDTO notesDTO) throws URISyntaxException {
        log.debug("REST request to save Notes : {}", notesDTO);
        APIResponseNotesDTO apiResponseNotesDTO = new APIResponseNotesDTO();
        if (notesDTO.getId() != null) {
            apiResponseNotesDTO.setCode("400");
            apiResponseNotesDTO.setStatus("FAILURE");
            apiResponseNotesDTO.setMessage("Id already exists");
            return apiResponseNotesDTO;
        }
        notesDTO.setCreatedDate(ZonedDateTime.now());
        Notes result = notesService.save(notesDTO);
        apiResponseNotesDTO.setStatus("SUCCESS");
        apiResponseNotesDTO.setCode("200");
        apiResponseNotesDTO.setMessage("OK");
        List<Notes> notes = new ArrayList<>();
        notes.add(result);
        apiResponseNotesDTO.setNotesList(notes);
        return apiResponseNotesDTO;
    }

    /**
     * PUT  /notes : Updates an existing notes.
     *
     * @param notesDTO the notesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notesDTO,
     * or with status 400 (Bad Request) if the notesDTO is not valid,
     * or with status 500 (Internal Server Error) if the notesDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notes")
    @Timed
    public APIResponseNotesDTO updateNotes(@RequestBody NotesDTO notesDTO) throws URISyntaxException {
        log.debug("REST request to update Notes : {}", notesDTO);
        if (notesDTO.getId() == null) {
            return createNotes(notesDTO);
        }
        notesDTO.setUpdatedDate(ZonedDateTime.now());
        Notes result = notesService.save(notesDTO);
        APIResponseNotesDTO apiResponseNotesDTO = new APIResponseNotesDTO();
        apiResponseNotesDTO.setCode("200");
        apiResponseNotesDTO.setMessage("OK");
        apiResponseNotesDTO.setStatus("SUCCESS");
        List<Notes> notes = new ArrayList<>();
        notes.add(result);
        apiResponseNotesDTO.setNotesList(notes);
        return apiResponseNotesDTO;
    }

    /**
     * GET  /notes : get all the notes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of notes in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/notes")
    @Timed
    public APIResponseNotesDTO getAllNotes(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Notes");
        APIResponseNotesDTO apiResponseNotesDTO = new APIResponseNotesDTO();
        apiResponseNotesDTO.setCode("200");
        apiResponseNotesDTO.setMessage("OK");
        apiResponseNotesDTO.setStatus("SUCCESS");
        List<Notes> result = notesService.findAll();
        apiResponseNotesDTO.setNotesList(result);
        return apiResponseNotesDTO;
    }

    /**
     * GET  /notes/:id : get the "id" notes.
     *
     * @param id the id of the notesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notes/{id}")
    @Timed
    public APIResponseNotesDTO getNotes(@PathVariable String id) {
        log.debug("REST request to get Notes : {}", id);
        Notes result = notesService.findOne(id);
        APIResponseNotesDTO apiResponseNotesDTO = new APIResponseNotesDTO();
        if(result != null){
            apiResponseNotesDTO.setCode("200");
            apiResponseNotesDTO.setMessage("OK");
            apiResponseNotesDTO.setStatus("SUCCESS");
            List<Notes> notes = new ArrayList<>();
            notes.add(result);
            apiResponseNotesDTO.setNotesList(notes);
            return apiResponseNotesDTO;
        }else{
            apiResponseNotesDTO.setCode("400");
            apiResponseNotesDTO.setMessage("No notes found");
            apiResponseNotesDTO.setStatus("FAILURE");
            return apiResponseNotesDTO;
        }

      /*  return Optional.ofNullable(notesDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
    }

    /**
     * DELETE  /notes/:id : delete the "id" notes.
     *
     * @param id the id of the notesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notes/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotes(@PathVariable String id) {
        log.debug("REST request to delete Notes : {}", id);
        notesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("notes", id.toString())).build();
    }

    /**
     * GET  /notes : get note*
     *
     * @param patientId the id of the patient
     * @param routineId the id of the routine
     * @return the ResponseEntity with status 200 (OK) and with body the notesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notes/patient")
    @Timed
    public APIResponseNotesDTO getNotesForAPatient(@RequestParam String patientId, @RequestParam String routineId) {
        log.debug("REST request to get Notes : {}", patientId);
        List<Notes> result = notesService.findAllByPatientIdAndRoutineId(patientId, routineId);
        APIResponseNotesDTO apiResponseNotesDTO = new APIResponseNotesDTO();
        if(result != null){
            apiResponseNotesDTO.setCode("200");
            apiResponseNotesDTO.setMessage("OK");
            apiResponseNotesDTO.setStatus("SUCCESS");
            apiResponseNotesDTO.setNotesList(result);
            return apiResponseNotesDTO;
        }else{
            apiResponseNotesDTO.setCode("400");
            apiResponseNotesDTO.setMessage("No notes found");
            apiResponseNotesDTO.setStatus("FAILURE");
            return apiResponseNotesDTO;
        }

    }

    /**
     * GET  /notes : get note*
     *
     * @param sessionId the id of the routine
     * @return the ResponseEntity with status 200 (OK) and with body the notesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notes/session")
    @Timed
    public APIResponseNotesDTO getNotesForASession(@RequestParam String sessionId) {
        log.debug("REST request to get Notes : {}", sessionId);
        List<Notes> result = notesService.findAllBySessionId(sessionId);
        APIResponseNotesDTO apiResponseNotesDTO = new APIResponseNotesDTO();
        if(result != null){
            apiResponseNotesDTO.setCode("200");
            apiResponseNotesDTO.setMessage("OK");
            apiResponseNotesDTO.setStatus("SUCCESS");
            apiResponseNotesDTO.setNotesList(result);
            return apiResponseNotesDTO;
        }else{
            apiResponseNotesDTO.setCode("400");
            apiResponseNotesDTO.setMessage("No notes found");
            apiResponseNotesDTO.setStatus("FAILURE");
            return apiResponseNotesDTO;
        }

    }

}
