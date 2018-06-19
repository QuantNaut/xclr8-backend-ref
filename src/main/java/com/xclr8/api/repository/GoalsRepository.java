package com.xclr8.api.repository;

import com.xclr8.api.domain.Goal;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Goals entity.
 */
@SuppressWarnings("unused")
public interface GoalsRepository extends MongoRepository<Goal,String> {
    List<Goal> findAllByPatientIdAndRoutineId(String patientId, String routineId);

    List<Goal> findAllByPatientIdAndBodyParts(String patientId, String bodyParts);

    List<Goal> findAllByDoctorId(String doctorId);

    List<Goal> findAllByDoctorIdAndPatientId(String doctorId, String patientId);
}
