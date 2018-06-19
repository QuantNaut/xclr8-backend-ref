package com.xclr8.api.service;

import com.xclr8.api.domain.BodyPart;
import com.xclr8.api.repository.BodyPartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing BodyPart.
 */
@Service
public class BodyPartService {

    private final Logger log = LoggerFactory.getLogger(BodyPartService.class);

    @Inject
    private BodyPartRepository bodyPartRepository;

    /**
     * Save a bodyPart.
     *
     * @param bodyPart the entity to save
     * @return the persisted entity
     */
    public BodyPart save(BodyPart bodyPart) {
        log.debug("Request to save BodyPart : {}", bodyPart);
        BodyPart result = bodyPartRepository.save(bodyPart);
        return result;
    }

    /**
     *  Get all the bodyParts.
     *
     *  @return the list of entities
     */
    public List<BodyPart> findAll() {
        log.debug("Request to get all BodyParts");
        List<BodyPart> result = bodyPartRepository.findAll();

        return result;
    }

    /**
     *  Get all the bodyParts.
     *
     *  @return the list of entities
     */
    public List<BodyPart> findAllByBodySectionId(String bodySectionId) {
        log.debug("Request to get all BodyParts");
        List<BodyPart> result = bodyPartRepository.findAllByBodySectionId(bodySectionId);

        return result;
    }

    /**
     *  Get one bodyPart by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public BodyPart findOne(String id) {
        log.debug("Request to get BodyPart : {}", id);
        BodyPart bodyPart = bodyPartRepository.findOne(id);
        return bodyPart;
    }

    /**
     *  Delete the  bodyPart by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete BodyPart : {}", id);
        bodyPartRepository.delete(id);
    }
}
