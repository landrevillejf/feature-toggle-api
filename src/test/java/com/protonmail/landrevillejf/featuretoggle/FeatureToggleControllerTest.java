package com.protonmail.landrevillejf.featuretoggle;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.protonmail.landrevillejf.featuretoggle.api.FeatureToggleController;
import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.service.common.ICommonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(FeatureToggleController.class)
public class FeatureToggleControllerTest {

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
    public void testGetAllCategories() throws Exception {
        FeatureToggle feature1 = new FeatureToggle("feature1", "Description1",true);
        FeatureToggle feature2 = new FeatureToggle("feature2", "Description2",false);
        List<FeatureToggle> categories = Arrays.asList(feature1, feature2);

        when(service.findAll(1, 15)).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/features")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(categories)));
    }
}
