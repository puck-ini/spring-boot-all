package org.zchzh.springdatajpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
public interface BaseCrudService<ENTITY, ID> {

    List<ENTITY> listAll();

    List<ENTITY> listAll(Sort sort);

    Page<ENTITY> listAll(Pageable pageable);

    List<ENTITY> listAll(Collection<ID> ids);

    List<ENTITY> listAll(Collection<ID> ids, Sort sort);

    Page<ENTITY> listAll(Collection<ID> ids, Pageable pageable);

    Optional<ENTITY> get(ID id);

    @Transactional(rollbackFor = Exception.class)
    ENTITY create(ENTITY entity);

    @Transactional(rollbackFor = Exception.class)
    List<ENTITY> createAll(Collection<ENTITY> entities);

    @Transactional(rollbackFor = Exception.class)
    ENTITY update(ENTITY entity);

    @Transactional(rollbackFor = Exception.class)
    List<ENTITY> updateAll(Collection<ENTITY> entities);

    @Transactional(rollbackFor = Exception.class)
    ENTITY remove(ID id);

    @Transactional(rollbackFor = Exception.class)
    List<ENTITY> removeAll(Collection<ID> ids);

    boolean isExist(ID id);

    long count();

    void flush();

}
