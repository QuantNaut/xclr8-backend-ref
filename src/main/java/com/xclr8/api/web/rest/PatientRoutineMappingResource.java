package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.PatientRoutineMapping;
import com.xclr8.api.dto.StatusResponse;
import com.xclr8.api.service.PatientRoutineMappingService;
import com.xclr8.api.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PatientRoutineMapping.
 */
@RestController
@RequestMapping("/api")
public class PatientRoutineMappingResource {

    private final Logger log = LoggerFactory.getLogger(PatientRoutineMappingResource.class);

    @Inject
    private PatientRoutineMappingService patientRoutineMappingService;

    /**
     * POST  /patient-routine-mappings : Create a new patientRoutineMapping.
     *
     * @param patientRoutineMapping the patientRoutineMapping to create
     * @return the ResponseEntity with status 201 (Created) and with body the new patientRoutineMapping, or with status 400 (Bad Request) if the patientRoutineMapping has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patient-routine-mappings")
    @Timed
    public ResponseEntity<StatusResponse<PatientRoutineMapping>> createPatientRoutineMapping(@RequestBody PatientRoutineMapping patientRoutineMapping) throws URISyntaxException {
        log.debug("REST request to save PatientRoutineMapping : {}", patientRoutineMapping);
        if (patientRoutineMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("patientRoutineMapping", "idexists", "A new patientRoutineMapping cannot already have an ID"))
                .body(new StatusResponse<>(400, "FAILURE", "Id exists"));
        }
        PatientRoutineMapping result = patientRoutineMappingService.save(patientRoutineMapping);
        return ResponseEntity.created(new URI("/api/patient-routine-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("patientRoutineMapping", result.getId().toString()))
            .body(new StatusResponse<>(200, "SUCCESS", "OK", result));
    }

    /**
     * PUT  /patient-routine-mappings : Updates an existing patientRoutineMapping.
     *
     * @param patientRoutineMapping the patientRoutineMapping to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated patientRoutineMapping,
     * or with status 400 (Bad Request) if the patientRoutineMapping is not valid,
     * or with status 500 (Internal Server Error) if the patientRoutineMapping couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patient-routine-mappings")
    @Timed
    public ResponseEntity<StatusResponse<PatientRoutineMapping>> updatePatientRoutineMapping(@RequestBody PatientRoutineMapping patientRoutineMapping) throws URISyntaxException {
        log.debug("REST request to update PatientRoutineMapping : {}", patientRoutineMapping);
        if (patientRoutineMapping.getId() == null) {
            return createPatientRoutineMapping(patientRoutineMapping);
        }
        PatientRoutineMapping result = patientRoutineMappingService.save(patientRoutineMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("patientRoutineMapping", patientRoutineMapping.getId().toString()))
            .body(new StatusResponse<>(200, "SUCCESS", "OK", result));
    }

    /**
     * GET  /patient-routine-mappings : get all the patientRoutineMappings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of patientRoutineMappings in body
     */
    @GetMapping("/patient-routine-mappings")
    @Timed
    public StatusResponse<List<PatientRoutineMapping>> getAllPatientRoutineMappings() {
        log.debug("REST request to get all PatientRoutineMappings");
        return new StatusResponse<>(200, "SUCCESS", "OK", patientRoutineMappingService.findAll());
    }

    /**
     * GET  /patient-routine-mappings/:id : get the "id" patientRoutineMapping.
     *
     * @param id the id of the patientRoutineMapping to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the patientRoutineMapping, or with status 404 (Not Found)
     */
    @GetMapping("/patient-routine-mappings/{id}")
    @Timed
    public ResponseEntity<StatusResponse<PatientRoutineMapping>> getPatientRoutineMapping(@PathVariable String id) {
        log.debug("REST request to get PatientRoutineMapping : {}", id);
        PatientRoutineMapping patientRoutineMapping = patientRoutineMappingService.findOne(id);
        return Optional.ofNullable(patientRoutineMapping)
            .map(result -> new ResponseEntity<>(
                new StatusResponse<>(200, "SUCCESS", "OK", result),
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(
                new StatusResponse<>(400, "FAILURE", "No entry found"),
                HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /patient-routine-mappings/:id : delete the "id" patientRoutineMapping.
     *
     * @param id the id of the patientRoutineMapping to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patient-routine-mappings/{id}")
    @Timed
    public ResponseEntity<StatusResponse<String>> deletePatientRoutineMapping(@PathVariable String id) {
        log.debug("REST request to delete PatientRoutineMapping : {}", id);
        patientRoutineMappingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("patientRoutineMapping", id.toString()))
            .body(new StatusResponse<>(200, "SUCCESS", "OK", "Entry deleted"));
    }

    /**
     * GET /patient-routine-mappings/patient : get all routines for a patient
     *
     * @param patientId patientId
     * @return returns Response entity with list of routines assigned for a patient
     */
    @GetMapping("/patient-routine-mapping/patient")
    @Timed
    public ResponseEntity<StatusResponse<List<PatientRoutineMapping>>> getRoutinesAssignedForAPatient(@RequestParam String patientId){
        log.debug("REST request to get Routines for a patient with patient id : {}", patientId);
        return ResponseEntity.ok(new StatusResponse<>(200, "SUCCESS", "OK", patientRoutineMappingService.getAllRoutinesForAPatient(patientId)));
    }

}
