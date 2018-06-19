package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.PatientBooking;
import com.xclr8.api.dto.APIResponseDTO;
import com.xclr8.api.dto.APIResponsePatientBookingDTO;
import com.xclr8.api.service.PatientBookingService;
import com.xclr8.api.web.rest.util.HeaderUtil;
import com.xclr8.api.web.rest.util.PaginationUtil;
import com.xclr8.api.service.dto.PatientBookingDTO;

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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing PatientBooking.
 */
@RestController
@RequestMapping("/api")
public class PatientBookingResource {

    private final Logger log = LoggerFactory.getLogger(PatientBookingResource.class);

    @Inject
    private PatientBookingService patientBookingService;

    /**
     * POST  /patient-bookings : Create a new patientBooking.
     *
     * @param patientBookingDTO the patientBookingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new patientBookingDTO, or with status 400 (Bad Request) if the patientBooking has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/patient-bookings")
    @Timed
    public APIResponsePatientBookingDTO createPatientBooking(@RequestBody PatientBookingDTO patientBookingDTO) throws URISyntaxException {
        log.debug("REST request to save PatientBooking : {}", patientBookingDTO);
        APIResponsePatientBookingDTO apiResponsePatientBookingDTO = new APIResponsePatientBookingDTO();
        if (patientBookingDTO.getId() != null) {
            apiResponsePatientBookingDTO.setMessage("Routine already exists");
            apiResponsePatientBookingDTO.setCode("400");
            apiResponsePatientBookingDTO.setStatus("FAILURE");
        }else{

            List<PatientBookingDTO> patientBookingDTOS = new ArrayList<>();
            PatientBookingDTO result = patientBookingService.save(patientBookingDTO);
            patientBookingDTOS.add(result);
            apiResponsePatientBookingDTO.setData(patientBookingDTOS);
            apiResponsePatientBookingDTO.setStatus("SUCCESS");
            apiResponsePatientBookingDTO.setCode("200");
            apiResponsePatientBookingDTO.setMessage("OK");
        }
        return apiResponsePatientBookingDTO;
    }

    /**
     * PUT  /patient-bookings : Updates an existing patientBooking.
     *
     * @param patientBookingDTO the patientBookingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated patientBookingDTO,
     * or with status 400 (Bad Request) if the patientBookingDTO is not valid,
     * or with status 500 (Internal Server Error) if the patientBookingDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/patient-bookings")
    @Timed
    public APIResponsePatientBookingDTO updatePatientBooking(@RequestBody PatientBookingDTO patientBookingDTO) throws URISyntaxException {
        log.debug("REST request to update PatientBooking : {}", patientBookingDTO);
        if (patientBookingDTO.getId() == null) {
            return createPatientBooking(patientBookingDTO);
        }
        APIResponsePatientBookingDTO apiResponsePatientBookingDTO = new APIResponsePatientBookingDTO();
        List<PatientBookingDTO> patientBookingDTOS = new ArrayList<>();
        PatientBookingDTO result = patientBookingService.save(patientBookingDTO);
        patientBookingDTOS.add(result);
        apiResponsePatientBookingDTO.setData(patientBookingDTOS);
        apiResponsePatientBookingDTO.setStatus("SUCCESS");
        apiResponsePatientBookingDTO.setCode("200");
        apiResponsePatientBookingDTO.setMessage("OK");
        return apiResponsePatientBookingDTO;

    }

    /**
     * GET  /patient-bookings : get all the patientBookings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of patientBookings in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/patient-bookings")
    @Timed
    public APIResponsePatientBookingDTO getAllPatientBookings(@ApiParam Pageable pageable)
        throws URISyntaxException {
        APIResponsePatientBookingDTO apiResponsePatientBookingDTO = new APIResponsePatientBookingDTO();
        apiResponsePatientBookingDTO.setStatus("SUCCESS");
        apiResponsePatientBookingDTO.setCode("200");
        apiResponsePatientBookingDTO.setMessage("OK");
        log.debug("REST request to get a page of PatientBookings");
        List<PatientBookingDTO> result = patientBookingService.findAll();
        apiResponsePatientBookingDTO.setData(result);
        return apiResponsePatientBookingDTO;
    }

    /**
     * GET  /patient-bookings/:id : get the "id" patientBooking.
     *
     * @param id the id of the patientBookingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the patientBookingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/patient-bookings/{id}")
    @Timed
    public APIResponsePatientBookingDTO getPatientBooking(@PathVariable String id) {
        log.debug("REST request to get PatientBooking : {}", id);
        PatientBookingDTO routine = patientBookingService.findOne(id);
        APIResponsePatientBookingDTO apiResponsePatientBookingDTO = new APIResponsePatientBookingDTO();
        if(routine != null){
            apiResponsePatientBookingDTO.setMessage("OK");
            apiResponsePatientBookingDTO.setStatus("SUCCESS");
            apiResponsePatientBookingDTO.setCode("200");
            List<PatientBookingDTO> patientBookingDTOs = new ArrayList<>();
            patientBookingDTOs.add(routine);
            apiResponsePatientBookingDTO.setData(patientBookingDTOs);
        }else{
            apiResponsePatientBookingDTO.setMessage("No Routine found");
            apiResponsePatientBookingDTO.setStatus("FAILURE");
            apiResponsePatientBookingDTO.setCode("204");
        }
        return  apiResponsePatientBookingDTO;
    }

    /**
     * DELETE  /patient-bookings/:id : delete the "id" patientBooking.
     *
     * @param id the id of the patientBookingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/patient-bookings/{id}")
    @Timed
    public ResponseEntity<Void> deletePatientBooking(@PathVariable String id) {
        log.debug("REST request to delete PatientBooking : {}", id);
        patientBookingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("patientBooking", id.toString())).build();
    }

}
