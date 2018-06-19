package com.xclr8.api.repository;

import com.xclr8.api.domain.BodySection;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the BodySection entity.
 */
@SuppressWarnings("unused")
public interface BodySectionRepository extends MongoRepository<BodySection,String> {

}
