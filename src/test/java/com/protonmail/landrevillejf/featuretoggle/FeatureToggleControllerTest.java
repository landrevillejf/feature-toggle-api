package com.protonmail.landrevillejf.featuretoggle;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.protonmail.landrevillejf.featuretoggle.api.FeatureToggleController;
import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.service.common.ICommonService;
import com.protonmail.landrevillejf.featuretoggle.util.UUIDGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(FeatureToggleController.class)
public class FeatureToggleControllerTest {

    @SpringBootTest
    @Configuration
    static class TestConfiguration {
        // Define test-specific beans or configuration here
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICommonService<FeatureToggle> service;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFeatures() throws Exception {
        FeatureToggle feature1 = new FeatureToggle("feature1", "Description1",true);
        feature1.setUid(UUIDGenerator.generateType1UUID().toString());
        FeatureToggle feature2 = new FeatureToggle("feature2", "Description2",false);
        feature2.setUid(UUIDGenerator.generateType1UUID().toString());
        List<FeatureToggle> featureToggles = Arrays.asList(feature1, feature2);

        when(service.findAll(1, 15)).thenReturn(featureToggles);

        mockMvc.perform(MockMvcRequestBuilders.get("/feature-toggle-api/features")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(featureToggles)));
    }
}
