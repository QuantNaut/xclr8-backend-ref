package com.xclr8.api.service.mapper;

import com.xclr8.api.domain.*;
import com.xclr8.api.service.dto.PatientBookingDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity PatientBooking and its DTO PatientBookingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PatientBookingMapper {

    PatientBookingDTO patientBookingToPatientBookingDTO(PatientBooking patientBooking);

    List<PatientBookingDTO> patientBookingsToPatientBookingDTOs(List<PatientBooking> patientBookings);

    PatientBooking patientBookingDTOToPatientBooking(PatientBookingDTO patientBookingDTO);

    List<PatientBooking> patientBookingDTOsToPatientBookings(List<PatientBookingDTO> patientBookingDTOs);
}
