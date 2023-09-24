package com.protonmail.landrevillejf.featuretoggle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeatureToggleTest {

    private FeatureToggle featureToggle;

    @BeforeEach
    public void setUp() {
        featureToggle = new FeatureToggle();
    }

    @Test
    public void testGetName() {
        featureToggle.setName("Test featureToggle");
        assertEquals("Test featureToggle", featureToggle.getName());
    }

    @Test
    public void testGetDescription() {
        featureToggle.setDescription("Test Description");
        assertEquals("Test Description", featureToggle.getDescription());
    }

    @Test
    public void testConstructorWithParameters() {
        FeatureToggle featureToggle = new FeatureToggle("Test Name", "Test Description",false);
        assertEquals("Test Name", featureToggle.getName());
        assertEquals("Test Description", featureToggle.getDescription());
        assertFalse(featureToggle.isEnabled());
    }
}

