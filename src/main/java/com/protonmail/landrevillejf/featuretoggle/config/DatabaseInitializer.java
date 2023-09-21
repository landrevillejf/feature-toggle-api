package com.protonmail.landrevillejf.featuretoggle.config;

import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.repository.FeatureToggleRepository;
import com.protonmail.landrevillejf.featuretoggle.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    private final FeatureToggleRepository featureToggleRepository;

    public DatabaseInitializer(FeatureToggleRepository featureToggleRepository) {
        this.featureToggleRepository = featureToggleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeFeaturesToggles();
    }

    private void initializeFeaturesToggles() {
        if (!featureToggleRepository.existsByName("signin")) {
            FeatureToggle featureToggle = new FeatureToggle();
            featureToggle.setUid(UUIDGenerator.generateType1UUID().toString());
            featureToggle.setName("signin");
            featureToggle.setDescription("Sign in Feature Toggle");
            featureToggle.setEnabled(true); // Set the default value as needed
            featureToggleRepository.save(featureToggle);
            logger.info("featureToggle " + "signin" + " inserted into the database.");
        } else {
            logger.info("featureToggle " + "signin" + " already exists the database.");
        }

        if (!featureToggleRepository.existsByName("signup")) {
            FeatureToggle featureToggle = new FeatureToggle();
            featureToggle.setUid(UUIDGenerator.generateType1UUID().toString());
            featureToggle.setName("signup");
            featureToggle.setDescription("Signup in Feature Toggle");
            featureToggle.setEnabled(true); // Set the default value as needed
            featureToggleRepository.save(featureToggle);
            logger.info("featureToggle " + "signup" + " inserted into the database.");
        } else {
            logger.info("featureToggle " + "signup" + " already exists the database.");
        }
    }
}

