package com.protonmail.landrevillejf.featuretoggle.service.common;

import com.protonmail.landrevillejf.featuretoggle.entity.common.CommonEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICommonService<E extends CommonEntity> {

    List<E> findAll(int page,int limit);
    List<E> findAllByCriteria(int page, int limit, @Param("search") String search);
    E findById(long id);
    E findByUid(String uid);
    E save(E entity);
    E update(E entity);
    E update(E entity,String uid);
    void delete(E entity);
    void deleteByUid(String uid);
    void deleteAll();
    long count();
}
