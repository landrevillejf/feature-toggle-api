package com.protonmail.landrevillejf.featuretoggle.util;

import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.entity.dto.FeatureToggleDto;
import org.modelmapper.ModelMapper;

public class DtoToObjectConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public DtoToObjectConverter(ModelMapper modelMapper) {
        DtoToObjectConverter.modelMapper = modelMapper;
    }

    public static FeatureToggle convertUserDtoToUser(FeatureToggleDto featureToggleDto) {
        return modelMapper.map(featureToggleDto, FeatureToggle.class);
    }
}
