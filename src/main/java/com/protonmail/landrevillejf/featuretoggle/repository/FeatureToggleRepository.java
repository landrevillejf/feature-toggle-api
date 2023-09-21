package com.protonmail.landrevillejf.featuretoggle.repository;


import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.repository.common.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureToggleRepository extends CommonRepository<FeatureToggle> {
    FeatureToggle findByName(String name);

    boolean existsByName(String statusName);

    Page<FeatureToggle> findByNameContaining(Pageable pageable, String search);

    boolean findByEnabled(boolean enabled);

    boolean findByNameAndEnabled(String name, boolean enabled);
}
