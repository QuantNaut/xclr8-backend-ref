package com.xclr8.api.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.xclr8.api.domain.BodyPart;
import com.xclr8.api.service.BodyPartService;
import com.xclr8.api.dto.APIResponseBodyPartDTO;
import com.xclr8.api.web.rest.util.HeaderUtil;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BodyPart.
 */
@RestController
@RequestMapping("/api")
public class BodyPartResource {

    private final Logger log = LoggerFactory.getLogger(BodyPartResource.class);

    @Inject
    private BodyPartService bodyPartService;

    /**
     * POST  /body-parts : Create a new bodyPart.
     *
     * @param bodyPart the bodyPart to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bodyPart, or with status 400 (Bad Request) if the bodyPart has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/body-parts")
    @Timed
    public ResponseEntity<BodyPart> createBodyPart(@RequestBody BodyPart bodyPart) throws URISyntaxException {
        log.debug("REST request to save BodyPart : {}", bodyPart);
        if (bodyPart.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("bodyPart", "idexists", "A new bodyPart cannot already have an ID")).body(null);
        }
        BodyPart result = bodyPartService.save(bodyPart);
        return ResponseEntity.created(new URI("/api/body-parts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bodyPart", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /body-parts : Updates an existing bodyPart.
     *
     * @param bodyPart the bodyPart to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bodyPart,
     * or with status 400 (Bad Request) if the bodyPart is not valid,
     * or with status 500 (Internal Server Error) if the bodyPart couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @ApiOperation(value = "xyz", hidden = true)
    @PutMapping("/body-parts")
    @Timed
    public ResponseEntity<BodyPart> updateBodyPart(@RequestBody BodyPart bodyPart) throws URISyntaxException {
        log.debug("REST request to update BodyPart : {}", bodyPart);
        if (bodyPart.getId() == null) {
            return createBodyPart(bodyPart);
        }
        BodyPart result = bodyPartService.save(bodyPart);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bodyPart", bodyPart.getId().toString()))
            .body(result);
    }

    /**
     * GET  /body-parts : get all the bodyParts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bodyParts in body
     */
    @GetMapping("/body-parts")
    @Timed
    public APIResponseBodyPartDTO getAllBodyParts() {
        log.debug("REST request to get all BodyParts");
        List<BodyPart> bodyParts = bodyPartService.findAll();
        APIResponseBodyPartDTO apiResponseBodyPartDTO = new APIResponseBodyPartDTO();
        apiResponseBodyPartDTO.setMessage("OK");
        apiResponseBodyPartDTO.setStatus("SUCCESS");
        apiResponseBodyPartDTO.setCode("200");
        apiResponseBodyPartDTO.setBodyParts(bodyParts);
        return apiResponseBodyPartDTO;
    }


    /**
     * GET  /body-parts : get all the bodyParts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of bodyParts in body
     */
    @GetMapping("/body-parts/section-parts")
    @Timed
    public APIResponseBodyPartDTO getAllBodyPartsForSection(@RequestParam String bodySectionId) {
        log.debug("REST request to get all BodyParts");
        List<BodyPart> bodyParts = bodyPartService.findAllByBodySectionId(bodySectionId);
        APIResponseBodyPartDTO apiResponseBodyPartDTO = new APIResponseBodyPartDTO();
        apiResponseBodyPartDTO.setMessage("OK");
        apiResponseBodyPartDTO.setStatus("SUCCESS");
        apiResponseBodyPartDTO.setCode("200");
        apiResponseBodyPartDTO.setBodyParts(bodyParts);
        return apiResponseBodyPartDTO;
    }

    /**
     * GET  /body-parts/:id : get the "id" bodyPart.
     *
     * @param id the id of the bodyPart to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bodyPart, or with status 404 (Not Found)
     */
    @ApiOperation(value = "xyz", hidden = true)
    @GetMapping("/body-parts/{id}")
    @Timed
    public ResponseEntity<BodyPart> getBodyPart(@PathVariable String id) {
        log.debug("REST request to get BodyPart : {}", id);
        BodyPart bodyPart = bodyPartService.findOne(id);
        return Optional.ofNullable(bodyPart)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /body-parts/:id : delete the "id" bodyPart.
     *
     * @param id the id of the bodyPart to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @ApiOperation(value = "xyz", hidden = true)
    @DeleteMapping("/body-parts/{id}")
    @Timed
    public ResponseEntity<Void> deleteBodyPart(@PathVariable String id) {
        log.debug("REST request to delete BodyPart : {}", id);
        bodyPartService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("bodyPart", id.toString())).build();
    }

}
