package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.BodySection;
import com.xclr8.api.service.BodySectionService;
import com.xclr8.api.dto.APIResponseBodySectionDTO;
import com.xclr8.api.web.rest.util.HeaderUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BodySection.
 */
@RestController
@RequestMapping("/api")
public class BodySectionResource {

    private final Logger log = LoggerFactory.getLogger(BodySectionResource.class);

    @Inject
    private BodySectionService bodySectionService;

    /**
     * POST  /body-sections : Create a new bodySection.
     *
     * @param bodySection the bodySection to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bodySection, or with status 400 (Bad Request) if the bodySection has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/body-sections")
    @Timed
    public ResponseEntity<BodySection> createBodySection(@RequestBody BodySection bodySection) throws URISyntaxException {
        log.debug("REST request to save BodySection : {}", bodySection);
        if (bodySection.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("bodySection", "idexists", "A new bodySection cannot already have an ID")).body(null);
        }
        BodySection result = bodySectionService.save(bodySection);
        return ResponseEntity.created(new URI("/api/body-sections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bodySection", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /body-sections : Updates an existing bodySection.
     *
     * @param bodySection the bodySection to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bodySection,
     * or with status 400 (Bad Request) if the bodySection is not valid,
     * or with status 500 (Internal Server Error) if the bodySection couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "xyz", hidden = true)
    @PutMapping("/body-sections")
    @Timed
    public ResponseEntity<BodySection> updateBodySection(@RequestBody BodySection bodySection) throws URISyntaxException {
        log.debug("REST request to update BodySection : {}", bodySection);
        if (bodySection.getId() == null) {
            return createBodySection(bodySection);
        }
        BodySection result = bodySectionService.save(bodySection);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bodySection", bodySection.getId().toString()))
            .body(result);
    }

    /**
     * GET  /body-sections : get all the bodySections.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bodySections in body
     */
    @GetMapping("/body-sections")
    @Timed
    public APIResponseBodySectionDTO getAllBodySections() {
        log.debug("REST request to get all BodySections");
        List<BodySection> bodySections = bodySectionService.findAll();
        APIResponseBodySectionDTO apiResponseBodySectionDTO = new APIResponseBodySectionDTO();
        apiResponseBodySectionDTO.setMessage("OK");
        apiResponseBodySectionDTO.setCode("200");
        apiResponseBodySectionDTO.setStatus("SUCCESS");
        apiResponseBodySectionDTO.setBodySections(bodySections);
        return apiResponseBodySectionDTO;
    }

    /**
     * GET  /body-sections/:id : get the "id" bodySection.
     *
     * @param id the id of the bodySection to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bodySection, or with status 404 (Not Found)
     */
    @GetMapping("/body-sections/{id}")
    @Timed
    @ApiOperation(value = "xyz", hidden = true)
    public ResponseEntity<BodySection> getBodySection(@PathVariable String id) {
        log.debug("REST request to get BodySection : {}", id);
        BodySection bodySection = bodySectionService.findOne(id);
        return Optional.ofNullable(bodySection)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /body-sections/:id : delete the "id" bodySection.
     *
     * @param id the id of the bodySection to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @ApiOperation(value = "xyz", hidden = true)
    @DeleteMapping("/body-sections/{id}")
    @Timed
    public ResponseEntity<Void> deleteBodySection(@PathVariable String id) {
        log.debug("REST request to delete BodySection : {}", id);
        bodySectionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("bodySection", id.toString())).build();
    }

}
