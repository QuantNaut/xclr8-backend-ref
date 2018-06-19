package com.xclr8.api.service;

import com.xclr8.api.domain.Routine;
import com.xclr8.api.repository.CustomRoutineRepository;
import com.xclr8.api.repository.RoutineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing Routine.
 */
@Service
public class RoutineService {

    private final Logger log = LoggerFactory.getLogger(RoutineService.class);

    @Inject
    private RoutineRepository routineRepository;

    @Inject
    private CustomRoutineRepository customRoutineRepository;

    /**
     * Save a routine.
     *
     * @param routine the entity to save
     * @return the persisted entity
     */
    public Routine save(Routine routine) {
        log.debug("Request to save Routine : {}", routine);
        Routine result = routineRepository.save(routine);
        return result;
    }

    /**
     *  Get all the routines.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<Routine> findAll(Pageable pageable) {
        log.debug("Request to get all Routines");
        Page<Routine> result = routineRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get all the routines.
     *
     *  @return the list of entities
     */
    public List<Routine> search(String routineBodySection, String routineBodyParts) {
        log.debug("Request to get search Routines");
        List<Routine> result = customRoutineRepository.findAllRoutinesBySearchCriteria(routineBodySection, routineBodyParts);
        return result;
    }

    /**
     *  Get all the routines.
     *
     *  @return the list of entities
     */
    public List<Routine> findAll() {
        log.debug("Request to get all Routines");
        return routineRepository.findAll();
    }

    /**
     *  Get one routine by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Routine findOne(String id) {
        log.debug("Request to get Routine : {}", id);
        Routine routine = routineRepository.findOne(id);
        return routine;
    }

    /**
     *  Delete the  routine by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Routine : {}", id);
        routineRepository.delete(id);
    }

    /**
     *  Get all the routines.
     *
     *  @return the list of entities
     */
    public List<Routine> findAllByDoctorId(String doctorId) {
        log.debug("Request to get all Routines by doctorId");
        return routineRepository.findAllByCreatedBy(doctorId);
    }
}
