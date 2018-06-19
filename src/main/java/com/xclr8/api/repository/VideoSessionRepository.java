package com.xclr8.api.repository;

import com.xclr8.api.domain.VideoSession;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
/**
 * Spring Data MongoDB repository for the VideoSession entity.
 */
@SuppressWarnings("unused")
public interface VideoSessionRepository extends MongoRepository<VideoSession,String> {

    VideoSession findByPatientIdOrderByIdDesc(String patientId);

    List<VideoSession> findAllByPatientIdAndDoctorId(String patientId, String doctorId);

}
