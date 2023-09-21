package com.protonmail.landrevillejf.featuretoggle.repository.common;

import com.protonmail.landrevillejf.featuretoggle.entity.common.CommonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;

/**
//@NoRepositoryBean To exclude this Repository from being instantiated
**/
@NoRepositoryBean
public interface CommonRepository<E extends CommonEntity> extends JpaRepository<E, Long> {
    E findByUid(String uid);
}
