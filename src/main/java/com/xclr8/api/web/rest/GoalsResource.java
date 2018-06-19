package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.Goal;
import com.xclr8.api.dto.APIResponseGoalDTO;
import com.xclr8.api.service.GoalsService;
import com.xclr8.api.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Goals.
 */
@RestController
@RequestMapping("/api")
public class GoalsResource {

    private final Logger log = LoggerFactory.getLogger(GoalsResource.class);

    @Inject
    private GoalsService goalsService;

    /**
     * POST  /goals : Create a new goals.
     *
     * @param goal the goals to create
     * @return the ResponseEntity with status 201 (Created) and with body the new goals, or with status 400 (Bad Request) if the goals has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/goals")
    @Timed
    public APIResponseGoalDTO createGoals(@RequestBody Goal goal) throws URISyntaxException {
        log.debug("REST request to save Goals : {}", goal);
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        goal.setCreatedDate(ZonedDateTime.now());
        if (goal.getId() != null) {
            //return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("goals", "idexists", "A new goals cannot already have an ID")).body(null);
            apiResponseGoalDTO.setMessage("Id Exists");
            apiResponseGoalDTO.setCode("400");
            apiResponseGoalDTO.setStatus("FAILURE");
            return  apiResponseGoalDTO;
        }
        Goal result = goalsService.save(goal);
        apiResponseGoalDTO.setCode("200");
        apiResponseGoalDTO.setMessage("OK");
        apiResponseGoalDTO.setStatus("SUCCESS");
        List<Goal> goals = new ArrayList<>();
        goals.add(result);
        apiResponseGoalDTO.setGoals(goals);
       /* return ResponseEntity.created(new URI("/api/goals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("goals", result.getId().toString()))
            .body(result);*/
        return  apiResponseGoalDTO;
    }

    /**
     * PUT  /goals : Updates an existing goals.
     *
     * @param goal the goals to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated goals,
     * or with status 400 (Bad Request) if the goals is not valid,
     * or with status 500 (Internal Server Error) if the goals couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/goals")
    @Timed
    public APIResponseGoalDTO updateGoals(@RequestBody Goal goal) throws URISyntaxException {
        log.debug("REST request to update Goals : {}", goal);
        if (goal.getId() == null) {
            return createGoals(goal);
        }
        goal.setUpdatedDate(ZonedDateTime.now());
        Goal result = goalsService.save(goal);
        /*return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("goals", goal.getId().toString()))
            .body(result);*/
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        apiResponseGoalDTO.setStatus("SUCCESS");
        apiResponseGoalDTO.setCode("200");
        apiResponseGoalDTO.setMessage("OK");
        List<Goal> goals = new ArrayList<>();
        goals.add(result);
        apiResponseGoalDTO.setGoals(goals);
        return apiResponseGoalDTO;
    }

    /**
     * GET  /goals : get all the goals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of goals in body
     */
    @GetMapping("/goals")
    @Timed
    public APIResponseGoalDTO getAllGoals() {
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        apiResponseGoalDTO.setStatus("SUCCESS");
        apiResponseGoalDTO.setCode("200");
        apiResponseGoalDTO.setMessage("OK");
        log.debug("REST request to get all Goals");
        List<Goal> goals = goalsService.findAll();
        apiResponseGoalDTO.setGoals(goals);
        return apiResponseGoalDTO;
    }

    /**
     * GET  /goals : get all the goals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of goals in body
     */
    @GetMapping("/goals/patient-routine")
    @Timed
    public APIResponseGoalDTO getAllGoalsForAPatientForARoutine(@RequestParam String patientId, @RequestParam String routineId) {
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        apiResponseGoalDTO.setStatus("SUCCESS");
        apiResponseGoalDTO.setCode("200");
        apiResponseGoalDTO.setMessage("OK");
        log.debug("REST request to get all Goals");
        List<Goal> goals = goalsService.findAllByPatientIdAndRoutineId(patientId, routineId);
        apiResponseGoalDTO.setGoals(goals);
        return apiResponseGoalDTO;
    }

    /**
     * GET  /goals : get all the goals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of goals in body
     */
    @GetMapping("/goals/doctor")
    @Timed
    public APIResponseGoalDTO getAllGoalsForADoctor(@RequestParam String doctorId) {
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        apiResponseGoalDTO.setStatus("SUCCESS");
        apiResponseGoalDTO.setCode("200");
        apiResponseGoalDTO.setMessage("OK");
        log.debug("REST request to get all Goals");
        List<Goal> goals = goalsService.findAllByDoctorId(doctorId);
        apiResponseGoalDTO.setGoals(goals);
        return apiResponseGoalDTO;
    }

    /**
     * GET  /goals : get all the goals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of goals in body
     */
    @GetMapping("/goals/doctor-patient")
    @Timed
    public APIResponseGoalDTO getAllGoalsForADoctorAndPatient(@RequestParam String doctorId, @RequestParam String patientId) {
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        apiResponseGoalDTO.setStatus("SUCCESS");
        apiResponseGoalDTO.setCode("200");
        apiResponseGoalDTO.setMessage("OK");
        log.debug("REST request to get all Goals");
        List<Goal> goals = goalsService.findAllByDoctorIdAndPatientId(doctorId, patientId);
        apiResponseGoalDTO.setGoals(goals);
        return apiResponseGoalDTO;
    }

    /**
     * GET  /goals : get all the goals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of goals in body
     */
    @GetMapping("/goals/patient-bodypart")
    @Timed
    public APIResponseGoalDTO getAllGoalsForAPatientForABodyPart(@RequestParam String patientId, @RequestParam String bodyPart) {
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        apiResponseGoalDTO.setStatus("SUCCESS");
        apiResponseGoalDTO.setCode("200");
        apiResponseGoalDTO.setMessage("OK");
        log.debug("REST request to get all Goals");
        List<Goal> goals = goalsService.findAllByPatientIdAndBodyParts(patientId, bodyPart);
        apiResponseGoalDTO.setGoals(goals);
        return apiResponseGoalDTO;
    }

    /**
     * GET  /goals/:id : get the "id" goals.
     *
     * @param id the id of the goals to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the goals, or with status 404 (Not Found)
     */
    @GetMapping("/goals/{id}")
    @Timed
    public APIResponseGoalDTO getGoal(@PathVariable String id) {
        log.debug("REST request to get Goals : {}", id);
        Goal goal = goalsService.findOne(id);
        APIResponseGoalDTO apiResponseGoalDTO = new APIResponseGoalDTO();
        if(goal != null){
            apiResponseGoalDTO.setStatus("SUCCESS");
            apiResponseGoalDTO.setCode("200");
            apiResponseGoalDTO.setMessage("OK");
            List<Goal> goals = new ArrayList<>();
            goals.add(goal);
            apiResponseGoalDTO.setGoals(goals);
        }else{
            apiResponseGoalDTO.setStatus("FAILURE");
            apiResponseGoalDTO.setMessage("No goals found");
            apiResponseGoalDTO.setCode("204");
        }
        return apiResponseGoalDTO;

       /* return Optional.ofNullable(goal)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));*/
    }

    /**
     * DELETE  /goals/:id : delete the "id" goals.
     *
     * @param id the id of the goals to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/goals/{id}")
    @Timed
    public ResponseEntity<Void> deleteGoals(@PathVariable String id) {
        log.debug("REST request to delete Goals : {}", id);
        goalsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("goals", id.toString())).build();
    }

}
