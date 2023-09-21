package com.protonmail.landrevillejf.featuretoggle.service.impl;

import com.protonmail.landrevillejf.featuretoggle.entity.FeatureToggle;
import com.protonmail.landrevillejf.featuretoggle.exception.ApiExceptionEnums;
import com.protonmail.landrevillejf.featuretoggle.exception.common.CommonApiException;
import com.protonmail.landrevillejf.featuretoggle.repository.FeatureToggleRepository;
import com.protonmail.landrevillejf.featuretoggle.service.common.ICommonService;
import com.protonmail.landrevillejf.featuretoggle.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FeatureToggleServiceImpl implements ICommonService<FeatureToggle> {

    @Autowired
    private FeatureToggleRepository repository;

    //region Find FeatureToggle
    @Override
    public List<FeatureToggle> findAll(int page, int limit) {
        if(page >0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<FeatureToggle> featureToggles= repository.findAll(pageable);
        return featureToggles.getContent();
    }

    @Override
    public List<FeatureToggle> findAllByCriteria(int page, int limit, String search) {
        if(page>0) page -=1;
        Pageable pageable= PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"id"));
        Page<FeatureToggle> featureTogglePage = null;
        if(search ==null){
            featureTogglePage= repository.findAll(pageable);
        }
        else if(search.isEmpty()){
            featureTogglePage= repository.findAll(pageable);
        }else{
            featureTogglePage= repository.findByNameContaining(pageable,search);
        }
        assert featureTogglePage != null;
        return featureTogglePage.getContent();
    }

    @Override
    public FeatureToggle findById(long id) {
        Optional <FeatureToggle> toggle = repository.findById(id);
        return toggle.orElse(null);
    }

    @Override
    public FeatureToggle findByUid(String uid) {
        return repository.findByUid(uid);
    }
    //endregion

    //region Save FeatureToggle
    @Override
    public FeatureToggle save(FeatureToggle entity) {
        String featureToggleName=(entity.getName().trim()).toLowerCase();
        FeatureToggle getFeatureToggle= repository.findByName(featureToggleName);
        if(getFeatureToggle != null){
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_EXIST_EXCEPTION.name());
        }
        entity.setUid(UUIDGenerator.generateType1UUID().toString());
        entity.setName(entity.getName().toLowerCase());
        entity.setEnabled(entity.isEnabled());
        entity.setDescription(entity.getDescription());
        return repository.save(entity);
    }
    //endregion

    //region Update FeatureToggle
    @Override
    public FeatureToggle update(FeatureToggle entity) {
        FeatureToggle toggle=findByUid(entity.getUid());
        if(toggle == null){
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        toggle.setName(entity.getName().toLowerCase());
        toggle.setDescription(entity.getDescription());
        toggle.setEnabled(entity.isEnabled());
        return repository.save(toggle);
    }

    @Override
    public FeatureToggle update(FeatureToggle entity, String uid) {
        FeatureToggle toggle=findByUid(uid);
        if(toggle == null){
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        toggle.setName(entity.getName().toLowerCase());
        toggle.setDescription(entity.getDescription());
        toggle.setEnabled(entity.isEnabled());
        return repository.save(toggle);
    }
    //endregion

    //region Delete FeatureToggle
    @Override
    public void delete(FeatureToggle entity) {
        int featureToggleListSize= repository.findAll().size();
        if(featureToggleListSize <1) {
            throw new CommonApiException(entity.getName() + " " + ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.delete(entity);
    }

    @Override
    public void deleteByUid(String uid) {
        FeatureToggle toggle=findByUid(uid);
        if(toggle == null){
            throw new CommonApiException(uid + " " + ApiExceptionEnums.OBJECT_NOT_FOUND.name());
        }
        repository.delete(toggle);
    }

    @Override
    public void deleteAll() {
        int size=repository.findAll().size();
        if(size <1) {
            throw new CommonApiException(ApiExceptionEnums.LIST_ALREADY_EMPTY.name());
        }
        repository.deleteAll();
    }
    //endregion

    //region Count FeatureToggle
    @Override
    public long count() {
        return repository.count();
    }

    public boolean isFeatureEnabled(String name, boolean enabled) {
        return repository.findByNameAndEnabled(name,enabled);
    }
    //endregion
}
