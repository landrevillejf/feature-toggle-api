package com.protonmail.landrevillejf.featuretoggle;


import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.repository.FeatureToggleRepository;
import com.protonmail.landrevillejf.featuretoggle.service.impl.FeatureToggleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class FeatureToggleServiceImplTest {

    @InjectMocks
    private FeatureToggleServiceImpl service;

    @Mock
    private FeatureToggleRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<FeatureToggle> features = new ArrayList<>();
        features.add(new FeatureToggle("FeatureToggle1", "Description1",true));
        features.add(new FeatureToggle("FeatureToggle2", "Description2",true));
        Page<FeatureToggle> categoryPage = new PageImpl<>(features);
        when(repository.findAll(any(Pageable.class))).thenReturn(categoryPage);
        List<FeatureToggle> result = service.findAll(1, 15);
        assertNotNull(result);
        assertEquals(features.size(), result.size());
        assertEquals(features, result);
    }
}
