package com.xclr8.api.web.rest;

import com.xclr8.api.Xclr8App;

import com.xclr8.api.domain.BodySection;
import com.xclr8.api.repository.BodySectionRepository;
import com.xclr8.api.service.BodySectionService;

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
 * Test class for the BodySectionResource REST controller.
 *
 * @see BodySectionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Xclr8App.class)
public class BodySectionResourceIntTest {

    private static final String DEFAULT_BODY_SECTION = "AAAAAAAAAA";
    private static final String UPDATED_BODY_SECTION = "BBBBBBBBBB";

    @Inject
    private BodySectionRepository bodySectionRepository;

    @Inject
    private BodySectionService bodySectionService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBodySectionMockMvc;

    private BodySection bodySection;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BodySectionResource bodySectionResource = new BodySectionResource();
        ReflectionTestUtils.setField(bodySectionResource, "bodySectionService", bodySectionService);
        this.restBodySectionMockMvc = MockMvcBuilders.standaloneSetup(bodySectionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BodySection createEntity() {
        BodySection bodySection = new BodySection()
                .bodySection(DEFAULT_BODY_SECTION);
        return bodySection;
    }

    @Before
    public void initTest() {
        bodySectionRepository.deleteAll();
        bodySection = createEntity();
    }

    @Test
    public void createBodySection() throws Exception {
        int databaseSizeBeforeCreate = bodySectionRepository.findAll().size();

        // Create the BodySection

        restBodySectionMockMvc.perform(post("/api/body-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bodySection)))
            .andExpect(status().isCreated());

        // Validate the BodySection in the database
        List<BodySection> bodySectionList = bodySectionRepository.findAll();
        assertThat(bodySectionList).hasSize(databaseSizeBeforeCreate + 1);
        BodySection testBodySection = bodySectionList.get(bodySectionList.size() - 1);
        assertThat(testBodySection.getBodySection()).isEqualTo(DEFAULT_BODY_SECTION);
    }

    @Test
    public void createBodySectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bodySectionRepository.findAll().size();

        // Create the BodySection with an existing ID
        BodySection existingBodySection = new BodySection();
        existingBodySection.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restBodySectionMockMvc.perform(post("/api/body-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBodySection)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<BodySection> bodySectionList = bodySectionRepository.findAll();
        assertThat(bodySectionList).hasSize(databaseSizeBeforeCreate);
    }

/*    @Test
    public void getAllBodySections() throws Exception {
        // Initialize the database
        bodySectionRepository.save(bodySection);

        // Get all the bodySectionList
        restBodySectionMockMvc.perform(get("/api/body-sections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bodySection.getId())))
            .andExpect(jsonPath("$.[*].bodySection").value(hasItem(DEFAULT_BODY_SECTION.toString())));
    }*/

    @Test
    public void getBodySection() throws Exception {
        // Initialize the database
        bodySectionRepository.save(bodySection);

        // Get the bodySection
        restBodySectionMockMvc.perform(get("/api/body-sections/{id}", bodySection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bodySection.getId()))
            .andExpect(jsonPath("$.bodySection").value(DEFAULT_BODY_SECTION.toString()));
    }

    @Test
    public void getNonExistingBodySection() throws Exception {
        // Get the bodySection
        restBodySectionMockMvc.perform(get("/api/body-sections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBodySection() throws Exception {
        // Initialize the database
        bodySectionService.save(bodySection);

        int databaseSizeBeforeUpdate = bodySectionRepository.findAll().size();

        // Update the bodySection
        BodySection updatedBodySection = bodySectionRepository.findOne(bodySection.getId());
        updatedBodySection
                .bodySection(UPDATED_BODY_SECTION);

        restBodySectionMockMvc.perform(put("/api/body-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBodySection)))
            .andExpect(status().isOk());

        // Validate the BodySection in the database
        List<BodySection> bodySectionList = bodySectionRepository.findAll();
        assertThat(bodySectionList).hasSize(databaseSizeBeforeUpdate);
        BodySection testBodySection = bodySectionList.get(bodySectionList.size() - 1);
        assertThat(testBodySection.getBodySection()).isEqualTo(UPDATED_BODY_SECTION);
    }

    @Test
    public void updateNonExistingBodySection() throws Exception {
        int databaseSizeBeforeUpdate = bodySectionRepository.findAll().size();

        // Create the BodySection

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBodySectionMockMvc.perform(put("/api/body-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bodySection)))
            .andExpect(status().isCreated());

        // Validate the BodySection in the database
        List<BodySection> bodySectionList = bodySectionRepository.findAll();
        assertThat(bodySectionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteBodySection() throws Exception {
        // Initialize the database
        bodySectionService.save(bodySection);

        int databaseSizeBeforeDelete = bodySectionRepository.findAll().size();

        // Get the bodySection
        restBodySectionMockMvc.perform(delete("/api/body-sections/{id}", bodySection.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BodySection> bodySectionList = bodySectionRepository.findAll();
        assertThat(bodySectionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
