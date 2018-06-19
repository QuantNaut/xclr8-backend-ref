package com.xclr8.api.service;

import com.xclr8.api.domain.PatientRoutineMapping;
import com.xclr8.api.repository.PatientRoutineMappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing PatientRoutineMapping.
 */
@Service
public class PatientRoutineMappingService {

    private final Logger log = LoggerFactory.getLogger(PatientRoutineMappingService.class);

    @Inject
    private PatientRoutineMappingRepository patientRoutineMappingRepository;

    /**
     * Save a patientRoutineMapping.
     *
     * @param patientRoutineMapping the entity to save
     * @return the persisted entity
     */
    public PatientRoutineMapping save(PatientRoutineMapping patientRoutineMapping) {
        log.debug("Request to save PatientRoutineMapping : {}", patientRoutineMapping);
        PatientRoutineMapping result = patientRoutineMappingRepository.save(patientRoutineMapping);
        return result;
    }

    /**
     *  Get all the patientRoutineMappings.
     *
     *  @return the list of entities
     */
    public List<PatientRoutineMapping> findAll() {
        log.debug("Request to get all PatientRoutineMappings");
        List<PatientRoutineMapping> result = patientRoutineMappingRepository.findAll();

        return result;
    }

    /**
     *  Get one patientRoutineMapping by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public PatientRoutineMapping findOne(String id) {
        log.debug("Request to get PatientRoutineMapping : {}", id);
        PatientRoutineMapping patientRoutineMapping = patientRoutineMappingRepository.findOne(id);
        return patientRoutineMapping;
    }

    /**
     *  Delete the  patientRoutineMapping by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete PatientRoutineMapping : {}", id);
        patientRoutineMappingRepository.delete(id);
    }

    /**
     * Get all routines assigned for a patient
     *
     * @param patientId patientId
     * @return list of routines for a patient
     */
    public List<PatientRoutineMapping> getAllRoutinesForAPatient(String patientId){
        log.debug("Request to get Routines for a patient with patient id : {}", patientId);
        return patientRoutineMappingRepository.findAllByPatientId(patientId);
    }
}
