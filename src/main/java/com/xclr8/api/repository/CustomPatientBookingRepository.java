package com.xclr8.api.repository;

import com.xclr8.api.domain.PatientBooking;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by shenju on 2/6/17.
 */
public interface CustomPatientBookingRepository  {
    List<PatientBooking> findAllByDoctorIdAndBookingDateAndTime(String doctorId, ZonedDateTime bookingDateAndTime);
}
