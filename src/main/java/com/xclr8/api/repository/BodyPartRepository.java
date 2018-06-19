package com.xclr8.api.repository;

import com.xclr8.api.domain.BodyPart;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the BodyPart entity.
 */
@SuppressWarnings("unused")
public interface BodyPartRepository extends MongoRepository<BodyPart,String> {

    List<BodyPart> findAllByBodySectionId(String bodySectionId);
}
