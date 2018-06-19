package com.xclr8.api.repository;

import com.xclr8.api.domain.PatientBooking;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data MongoDB repository for the PatientBooking entity.
 */
@SuppressWarnings("unused")
public interface PatientBookingRepository extends MongoRepository<PatientBooking,String> {

}
