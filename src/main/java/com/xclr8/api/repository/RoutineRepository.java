package com.xclr8.api.repository;

import com.xclr8.api.domain.Routine;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Routine entity.
 */
@SuppressWarnings("unused")
public interface RoutineRepository extends MongoRepository<Routine,String> {

    List<Routine> findAllByCreatedBy(String doctorId);

}
