package com.protonmail.landrevillejf.featuretoggle;

import com.protonmail.landrevillejf.featuretoggle.api.FeatureToggleController;
import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.service.common.ICommonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FeatureToggleController.class)
public class FeatureToggleIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICommonService<FeatureToggle> iCommonService;

    @Test
    public void testGetAllCategoriesEndpoint() throws Exception {
        FeatureToggle featureToggle = new FeatureToggle();
        featureToggle.setName("featureToggle Name");
        featureToggle.setDescription("featureToggle Description");
        featureToggle.setEnabled(true);
        Mockito.when(iCommonService.findAll(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(Collections.singletonList(featureToggle));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/features"))
                .andExpect(status().isOk());
    }
}



