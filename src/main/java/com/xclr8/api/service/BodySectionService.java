package com.xclr8.api.service;

import com.xclr8.api.domain.BodySection;
import com.xclr8.api.repository.BodySectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing BodySection.
 */
@Service
public class BodySectionService {

    private final Logger log = LoggerFactory.getLogger(BodySectionService.class);

    @Inject
    private BodySectionRepository bodySectionRepository;

    /**
     * Save a bodySection.
     *
     * @param bodySection the entity to save
     * @return the persisted entity
     */
    public BodySection save(BodySection bodySection) {
        log.debug("Request to save BodySection : {}", bodySection);
        BodySection result = bodySectionRepository.save(bodySection);
        return result;
    }

    /**
     *  Get all the bodySections.
     *
     *  @return the list of entities
     */
    public List<BodySection> findAll() {
        log.debug("Request to get all BodySections");
        List<BodySection> result = bodySectionRepository.findAll();

        return result;
    }

    /**
     *  Get one bodySection by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    public BodySection findOne(String id) {
        log.debug("Request to get BodySection : {}", id);
        BodySection bodySection = bodySectionRepository.findOne(id);
        return bodySection;
    }

    /**
     *  Delete the  bodySection by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete BodySection : {}", id);
        bodySectionRepository.delete(id);
    }
}
