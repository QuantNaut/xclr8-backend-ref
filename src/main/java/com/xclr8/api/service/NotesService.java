package com.xclr8.api.service;

import com.xclr8.api.domain.Notes;
import com.xclr8.api.repository.NotesRepository;
import com.xclr8.api.dto.NotesDTO;
import com.xclr8.api.service.mapper.NotesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Notes.
 */
@Service
public class NotesService {

    private final Logger log = LoggerFactory.getLogger(NotesService.class);

    @Inject
    private NotesRepository notesRepository;

    @Autowired
    private NotesMapper notesMapper;

    /**
     * Save a notes.
     *
     * @param notesDTO the entity to save
     * @return the persisted entity
     */
    public Notes save(NotesDTO notesDTO) {
        log.debug("Request to save Notes : {}", notesDTO);
        Notes notes = notesMapper.notesDTOToNotes(notesDTO);
        notes = notesRepository.save(notes);
        return notes;
    }

    /**
     *  Get all the notes.
     *
     *  @return the list of entities
     */
    public List<Notes> findAll() {
        log.debug("Request to get all Notes");
        List<Notes> result = notesRepository.findAll();
        return result;
    }

    /**
     *  Get one notes by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Notes findOne(String id) {
        log.debug("Request to get Notes : {}", id);
        Notes notes = notesRepository.findOne(id);
        return notes;
    }

    /**
     *  Get one notes by id.
     *
     *  @param patientId the id of the patient
     *  @param routineId the id of the routine
     *  @return the entity
     */
    public List<Notes> findAllByPatientIdAndRoutineId(String patientId, String routineId) {
        log.debug("Request to get Notes : {}", patientId);
        List<Notes> notes = notesRepository.findAllByPatientIdAndRoutineId(patientId, routineId);
        return notes;
    }


    /**
     *  Get one notes by id.
     *
     *
     *  @param sessionId the id of the routine
     *  @return the entity
     */
    public List<Notes> findAllBySessionId(String sessionId) {
        log.debug("Request to get Notes : {}", sessionId);
        List<Notes> notes = notesRepository.findAllBySessionId(sessionId);
        return notes;
    }

    /**
     *  Delete the  notes by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Notes : {}", id);
        notesRepository.delete(id);
    }
}
