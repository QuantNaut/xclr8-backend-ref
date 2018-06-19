package com.xclr8.api.repository;

import com.xclr8.api.domain.Session;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Session entity.
 */
@SuppressWarnings("unused")
public interface SessionRepository extends MongoRepository<Session,String> {

    List<Session> findAllByPatientId(String patientId);

    List<Session> findAllByIsActiveIsTrueAndPatientId(String patientId);

    List<Session> findAllByPatientIdAndDoctorId(String patientId, String doctorId);

}
