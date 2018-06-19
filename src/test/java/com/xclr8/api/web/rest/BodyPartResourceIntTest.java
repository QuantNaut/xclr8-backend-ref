package com.xclr8.api.web.rest;

import com.xclr8.api.Xclr8App;

import com.xclr8.api.domain.BodyPart;
import com.xclr8.api.repository.BodyPartRepository;
import com.xclr8.api.service.BodyPartService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BodyPartResource REST controller.
 *
 * @see BodyPartResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Xclr8App.class)
public class BodyPartResourceIntTest {

    private static final String DEFAULT_BODY_PART = "AAAAAAAAAA";
    private static final String UPDATED_BODY_PART = "BBBBBBBBBB";

    @Inject
    private BodyPartRepository bodyPartRepository;

    @Inject
    private BodyPartService bodyPartService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBodyPartMockMvc;

    private BodyPart bodyPart;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BodyPartResource bodyPartResource = new BodyPartResource();
        ReflectionTestUtils.setField(bodyPartResource, "bodyPartService", bodyPartService);
        this.restBodyPartMockMvc = MockMvcBuilders.standaloneSetup(bodyPartResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BodyPart createEntity() {
        BodyPart bodyPart = new BodyPart()
                .bodyPart(DEFAULT_BODY_PART);
        return bodyPart;
    }

    @Before
    public void initTest() {
        bodyPartRepository.deleteAll();
        bodyPart = createEntity();
    }

    @Test
    public void createBodyPart() throws Exception {
        int databaseSizeBeforeCreate = bodyPartRepository.findAll().size();

        // Create the BodyPart

        restBodyPartMockMvc.perform(post("/api/body-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bodyPart)))
            .andExpect(status().isCreated());

        // Validate the BodyPart in the database
        List<BodyPart> bodyPartList = bodyPartRepository.findAll();
        assertThat(bodyPartList).hasSize(databaseSizeBeforeCreate + 1);
        BodyPart testBodyPart = bodyPartList.get(bodyPartList.size() - 1);
        assertThat(testBodyPart.getBodyPart()).isEqualTo(DEFAULT_BODY_PART);
    }

    @Test
    public void createBodyPartWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bodyPartRepository.findAll().size();

        // Create the BodyPart with an existing ID
        BodyPart existingBodyPart = new BodyPart();
        existingBodyPart.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBodyPartMockMvc.perform(post("/api/body-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBodyPart)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<BodyPart> bodyPartList = bodyPartRepository.findAll();
        assertThat(bodyPartList).hasSize(databaseSizeBeforeCreate);
    }

   /* @Test
    public void getAllBodyParts() throws Exception {
        // Initialize the database
        bodyPartRepository.save(bodyPart);

        // Get all the bodyPartList
        restBodyPartMockMvc.perform(get("/api/body-parts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bodyPart.getId())))
            .andExpect(jsonPath("$.[*].bodyPart").value(hasItem(DEFAULT_BODY_PART.toString())));
    }
*/
    @Test
    public void getBodyPart() throws Exception {
        // Initialize the database
        bodyPartRepository.save(bodyPart);

        // Get the bodyPart
        restBodyPartMockMvc.perform(get("/api/body-parts/{id}", bodyPart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bodyPart.getId()))
            .andExpect(jsonPath("$.bodyPart").value(DEFAULT_BODY_PART.toString()));
    }

    @Test
    public void getNonExistingBodyPart() throws Exception {
        // Get the bodyPart
        restBodyPartMockMvc.perform(get("/api/body-parts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBodyPart() throws Exception {
        // Initialize the database
        bodyPartService.save(bodyPart);

        int databaseSizeBeforeUpdate = bodyPartRepository.findAll().size();

        // Update the bodyPart
        BodyPart updatedBodyPart = bodyPartRepository.findOne(bodyPart.getId());
        updatedBodyPart
                .bodyPart(UPDATED_BODY_PART);

        restBodyPartMockMvc.perform(put("/api/body-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBodyPart)))
            .andExpect(status().isOk());

        // Validate the BodyPart in the database
        List<BodyPart> bodyPartList = bodyPartRepository.findAll();
        assertThat(bodyPartList).hasSize(databaseSizeBeforeUpdate);
        BodyPart testBodyPart = bodyPartList.get(bodyPartList.size() - 1);
        assertThat(testBodyPart.getBodyPart()).isEqualTo(UPDATED_BODY_PART);
    }

    @Test
    public void updateNonExistingBodyPart() throws Exception {
        int databaseSizeBeforeUpdate = bodyPartRepository.findAll().size();

        // Create the BodyPart

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBodyPartMockMvc.perform(put("/api/body-parts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bodyPart)))
            .andExpect(status().isCreated());

        // Validate the BodyPart in the database
        List<BodyPart> bodyPartList = bodyPartRepository.findAll();
        assertThat(bodyPartList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteBodyPart() throws Exception {
        // Initialize the database
        bodyPartService.save(bodyPart);

        int databaseSizeBeforeDelete = bodyPartRepository.findAll().size();

        // Get the bodyPart
        restBodyPartMockMvc.perform(delete("/api/body-parts/{id}", bodyPart.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BodyPart> bodyPartList = bodyPartRepository.findAll();
        assertThat(bodyPartList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
