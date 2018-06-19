package com.xclr8.api.repository;

import com.xclr8.api.domain.Notes;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Notes entity.
 */
@SuppressWarnings("unused")
public interface NotesRepository extends MongoRepository<Notes,String> {
    List<Notes> findAllByPatientIdAndRoutineId(String patientId, String routineId);

    List<Notes> findAllBySessionId(String sessionId);
}
