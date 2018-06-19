package com.xclr8.api.repository;

import com.xclr8.api.domain.ExerciseSession;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Session entity.
 */
public interface ExerciseSessionRepository extends MongoRepository<ExerciseSession, String> {



}
