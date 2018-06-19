package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.Routine;
import com.xclr8.api.service.RoutineService;
import com.xclr8.api.dto.APIResponseRoutineDTO;
import com.xclr8.api.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Routine.
 */
@RestController
@RequestMapping("/api")
public class RoutineResource {

    private final Logger log = LoggerFactory.getLogger(RoutineResource.class);

    @Inject
    private RoutineService routineService;

    /**
     * POST  /routines : Create a new routine.
     *
     * @param routine the routine to create
     * @return the ResponseEntity with status 201 (Created) and with body the new routine, or with status 400 (Bad Request) if the routine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/routines")
    @Timed
    public APIResponseRoutineDTO createRoutine(@Valid @RequestBody Routine routine) throws URISyntaxException {
        log.debug("REST request to save Routine : {}", routine);
        APIResponseRoutineDTO apiResponseRoutineDTO = new APIResponseRoutineDTO();
        routine.setCreationDate(ZonedDateTime.now());
        if (routine.getId() != null) {
            //return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("routine", "idexists", "A new routine cannot already have an ID")).body(null);
            apiResponseRoutineDTO.setMessage("Routine already exists");
            apiResponseRoutineDTO.setCode("400");
            apiResponseRoutineDTO.setStatus("FAILURE");
        }else{
            Routine result = routineService.save(routine);
            List<Routine> routines = new ArrayList<>();
            routines.add(result);
            apiResponseRoutineDTO.setRoutines(routines);
            apiResponseRoutineDTO.setStatus("SUCCESS");
            apiResponseRoutineDTO.setCode("200");
            apiResponseRoutineDTO.setMessage("OK");
        }
        return apiResponseRoutineDTO;

        /*return ResponseEntity.created(new URI("/api/routines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("routine", result.getId().toString()))
            .body(result);*/
    }

    /**
     * PUT  /routines : Updates an existing routine.
     *
     * @param routine the routine to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated routine,
     * or with status 400 (Bad Request) if the routine is not valid,
     * or with status 500 (Internal Server Error) if the routine couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/routines")
    @Timed
    public APIResponseRoutineDTO updateRoutine(@Valid @RequestBody Routine routine) throws URISyntaxException {
        log.debug("REST request to update Routine : {}", routine);
        if (routine.getId() == null) {
            return createRoutine(routine);
        }
        routine.setUpdatedDate(ZonedDateTime.now());
        Routine result = routineService.save(routine);
        APIResponseRoutineDTO apiResponseRoutineDTO = new APIResponseRoutineDTO();
        List<Routine> routines = new ArrayList<>();
        routines.add(result);
        apiResponseRoutineDTO.setRoutines(routines);
        apiResponseRoutineDTO.setStatus("SUCCESS");
        apiResponseRoutineDTO.setCode("200");
        apiResponseRoutineDTO.setMessage("OK");
        return  apiResponseRoutineDTO;
        /*return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("routine", routine.getId().toString()))
            .body(result);*/
    }

    /**
     * GET  /routines : get all the routines.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of routines in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/routines")
    @Timed
   /* public ResponseEntity<List<Routine>> getAllRoutines(@ApiParam Pageable pageable)*/
    public APIResponseRoutineDTO getAllRoutines()
        throws URISyntaxException {
        log.debug("REST request to get a page of Routines");
        List<Routine> routines = routineService.findAll();
        APIResponseRoutineDTO apiResponseRoutineDTO = new APIResponseRoutineDTO();
        apiResponseRoutineDTO.setMessage("OK");
        apiResponseRoutineDTO.setStatus("SUCCESS");
        apiResponseRoutineDTO.setCode("200");
        apiResponseRoutineDTO.setRoutines(routines);
        return  apiResponseRoutineDTO;
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/routines");
        //return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /routines : get all the routines.
     * @param routineBodySection
     * @param routineBodyParts
     * @return the ResponseEntity with status 200 (OK) and the list of routines in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/routines/search")
    @Timed
   /* public ResponseEntity<List<Routine>> getAllRoutines(@ApiParam Pageable pageable)*/
    public APIResponseRoutineDTO searchRoutines(@RequestParam String routineBodySection, @RequestParam String routineBodyParts)
        throws URISyntaxException {
        log.debug("REST request to search for Routines");
        List<Routine> routines = routineService.search(routineBodySection, routineBodyParts);
        APIResponseRoutineDTO apiResponseRoutineDTO = new APIResponseRoutineDTO();
        apiResponseRoutineDTO.setMessage("OK");
        apiResponseRoutineDTO.setStatus("SUCCESS");
        apiResponseRoutineDTO.setCode("200");
        apiResponseRoutineDTO.setRoutines(routines);
        return  apiResponseRoutineDTO;
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/routines");
        //return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    /**
     * GET  /routines/:id : get the "id" routine.
     *
     * @param id the id of the routine to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the routine, or with status 404 (Not Found)
     */
    @GetMapping("/routines/{id}")
    @Timed
    public APIResponseRoutineDTO getRoutine(@PathVariable String id) {
        log.debug("REST request to get Routine : {}", id);
        Routine routine = routineService.findOne(id);
        APIResponseRoutineDTO apiResponseRoutineDTO = new APIResponseRoutineDTO();
        if(routine != null){
            apiResponseRoutineDTO.setMessage("OK");
            apiResponseRoutineDTO.setStatus("SUCCESS");
            apiResponseRoutineDTO.setCode("200");
            List<Routine> routines = new ArrayList<>();
            routines.add(routine);
            apiResponseRoutineDTO.setRoutines(routines);
        }else{
            apiResponseRoutineDTO.setMessage("No Routine found");
            apiResponseRoutineDTO.setStatus("FAILURE");
            apiResponseRoutineDTO.setCode("204");
        }
        return  apiResponseRoutineDTO;
    }

    /**
     * DELETE  /routines/:id : delete the "id" routine.
     *
     * @param id the id of the routine to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/routines/{id}")
    @Timed
    public ResponseEntity<Void> deleteRoutine(@PathVariable String id) {
        log.debug("REST request to delete Routine : {}", id);
        routineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("routine", id.toString())).build();
    }

    /**
     * GET  /routines : get all the routines by doctorId.
     * @param doctorId
     * @return the ResponseEntity with status 200 (OK) and the list of routines in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/routines/doctor")
    @Timed
   /* public ResponseEntity<List<Routine>> getAllRoutines(@ApiParam Pageable pageable)*/
    public APIResponseRoutineDTO getRoutinesForDoctor(@RequestParam String doctorId)
        throws URISyntaxException {
        log.debug("REST request to search for Routines by doctorId");
        List<Routine> routines = routineService.findAllByDoctorId(doctorId);
        APIResponseRoutineDTO apiResponseRoutineDTO = new APIResponseRoutineDTO();
        apiResponseRoutineDTO.setMessage("OK");
        apiResponseRoutineDTO.setStatus("SUCCESS");
        apiResponseRoutineDTO.setCode("200");
        apiResponseRoutineDTO.setRoutines(routines);
        return  apiResponseRoutineDTO;
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/routines");
        //return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
