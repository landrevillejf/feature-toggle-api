package com.protonmail.landrevillejf.featuretoggle.entity;

import com.protonmail.landrevillejf.featuretoggle.entity.common.CommonEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "features")
public class FeatureToggle extends CommonEntity {

    //region Fields Entity

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled",nullable = false)
    private boolean enabled;
}
