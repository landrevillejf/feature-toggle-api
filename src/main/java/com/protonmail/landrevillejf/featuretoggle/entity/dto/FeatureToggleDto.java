package com.protonmail.landrevillejf.featuretoggle.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureToggleDto {
    private String name;
    private String description;
    private boolean enabled;
}
