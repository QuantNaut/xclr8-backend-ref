package com.xclr8.api.service;

import com.xclr8.api.domain.PatientBooking;
import com.xclr8.api.repository.PatientBookingRepository;
import com.xclr8.api.service.dto.PatientBookingDTO;
import com.xclr8.api.service.mapper.PatientBookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing PatientBooking.
 */
@Service
public class PatientBookingService {

    private final Logger log = LoggerFactory.getLogger(PatientBookingService.class);

    @Inject
    private PatientBookingRepository patientBookingRepository;

    @Inject
    private PatientBookingMapper patientBookingMapper;

    /**
     * Save a patientBooking.
     *
     * @param patientBookingDTO the entity to save
     * @return the persisted entity
     */
    public PatientBookingDTO save(PatientBookingDTO patientBookingDTO) {
        log.debug("Request to save PatientBooking : {}", patientBookingDTO);
        PatientBooking patientBooking = patientBookingMapper.patientBookingDTOToPatientBooking(patientBookingDTO);
        patientBooking = patientBookingRepository.save(patientBooking);
        PatientBookingDTO result = patientBookingMapper.patientBookingToPatientBookingDTO(patientBooking);
        return result;
    }

    /**
     *  Get all the patientBookings.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    public Page<PatientBookingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientBookings");
        Page<PatientBooking> result = patientBookingRepository.findAll(pageable);
        return result.map(patientBooking -> patientBookingMapper.patientBookingToPatientBookingDTO(patientBooking));
    }

    /**
     *  Get all the patientBookings.
     *
     *  @return the list of entities
     */
    public List<PatientBookingDTO> findAll() {
        log.debug("Request to get all PatientBookings");
        List<PatientBooking> result = patientBookingRepository.findAll();
        List<PatientBookingDTO> patientBookingDTOS = patientBookingMapper.patientBookingsToPatientBookingDTOs(result);
        return  patientBookingDTOS;
    }

    /**

    /**
     *  Get one patientBooking by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public PatientBookingDTO findOne(String id) {
        log.debug("Request to get PatientBooking : {}", id);
        PatientBooking patientBooking = patientBookingRepository.findOne(id);
        PatientBookingDTO patientBookingDTO = patientBookingMapper.patientBookingToPatientBookingDTO(patientBooking);
        return patientBookingDTO;
    }

    /**
     *  Delete the  patientBooking by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete PatientBooking : {}", id);
        patientBookingRepository.delete(id);
    }
}
