package com.xclr8.api.service;

import com.xclr8.api.domain.Goal;
import com.xclr8.api.repository.GoalsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing Goals.
 */
@Service
public class GoalsService {

    private final Logger log = LoggerFactory.getLogger(GoalsService.class);

    @Inject
    private GoalsRepository goalsRepository;

    /**
     * Save a goals.
     *
     * @param goal the entity to save
     * @return the persisted entity
     */
    public Goal save(Goal goal) {
        log.debug("Request to save Goals : {}", goal);
        Goal result = goalsRepository.save(goal);
        return result;
    }

    /**
     *  Get all the goals.
     *
     *  @return the list of entities
     */
    public List<Goal> findAll() {
        log.debug("Request to get all Goals");
        List<Goal> result = goalsRepository.findAll();
        return result;
    }

    /**
     *  Get all the goals.
     *
     *  @return the list of entities
     */
    public List<Goal> findAllByPatientIdAndRoutineId(String patientId, String routineId) {
        log.debug("Request to get all Goals");
        List<Goal> result = goalsRepository.findAllByPatientIdAndRoutineId(patientId, routineId);
        return result;
    }


    /**
     *  Get all the goals.
     *
     *  @return the list of entities
     */
    public List<Goal> findAllByDoctorId(String doctorId) {
        log.debug("Request to get all Goals");
        List<Goal> result = goalsRepository.findAllByDoctorId(doctorId);
        return result;
    }
    /**
     *  Get all the goals.
     *
     *  @return the list of entities
     */
    public List<Goal> findAllByPatientIdAndBodyParts(String patientId, String bodyPart) {
        log.debug("Request to get all Goals");
        List<Goal> result = goalsRepository.findAllByPatientIdAndBodyParts(patientId, bodyPart);
        return result;
    }

    /**
     *  Get all the goals.
     *
     *  @return the list of entities
     */
    public List<Goal> findAllByDoctorIdAndPatientId(String doctorId, String patientId) {
        log.debug("Request to get all Goals");
        List<Goal> result = goalsRepository.findAllByDoctorIdAndPatientId(doctorId, patientId);
        return result;
    }

    /**
     *  Get one goals by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public Goal findOne(String id) {
        log.debug("Request to get Goals : {}", id);
        Goal goal = goalsRepository.findOne(id);
        return goal;
    }

    /**
     *  Delete the  goals by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Goals : {}", id);
        goalsRepository.delete(id);
    }
}
