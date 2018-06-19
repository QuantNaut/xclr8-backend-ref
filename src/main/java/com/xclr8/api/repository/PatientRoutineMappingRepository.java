package com.xclr8.api.repository;

import com.xclr8.api.domain.PatientRoutineMapping;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the PatientRoutineMapping entity.
 */
@SuppressWarnings("unused")
public interface PatientRoutineMappingRepository extends MongoRepository<PatientRoutineMapping,String> {
    List<PatientRoutineMapping> findAllByPatientId(String patientId);
}
