package org.zchzh.springdatajpa.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.zchzh.springdatajpa.repository.BaseRepo;
import org.zchzh.springdatajpa.service.BaseCrudService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
public abstract class AbstractCrudService<ENTITY, ID> implements BaseCrudService<ENTITY, ID> {

    private final BaseRepo<ENTITY, ID> repo;

    protected AbstractCrudService(BaseRepo<ENTITY, ID> repo) {
        this.repo = repo;
    }

    @Override
    public List<ENTITY> listAll() {
        return repo.findAll();
    }

    @Override
    public List<ENTITY> listAll(Sort sort) {
        return repo.findAll(sort);
    }

    @Override
    public Page<ENTITY> listAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<ENTITY> listAll(Collection<ID> ids) {
        return repo.findAllById(ids);
    }

    @Override
    public List<ENTITY> listAll(Collection<ID> ids, Sort sort) {
        return repo.findAllById(ids, sort);
    }

    @Override
    public Page<ENTITY> listAll(Collection<ID> ids, Pageable pageable) {
        return repo.findAllById(ids, pageable);
    }

    @Override
    public Optional<ENTITY> get(ID id) {
        return repo.findById(id);
    }

    @Override
    public ENTITY create(ENTITY entity) {
        return repo.save(entity);
    }

    @Override
    public List<ENTITY> createAll(Collection<ENTITY> entities) {
        return CollectionUtils.isEmpty(entities) ? Collections.emptyList() : repo.saveAll(entities);
    }

    @Override
    public ENTITY update(ENTITY entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    public List<ENTITY> updateAll(Collection<ENTITY> entities) {
        return CollectionUtils.isEmpty(entities) ? Collections.emptyList() : repo.saveAll(entities);
    }

    @Override
    public ENTITY remove(ID id) {
        ENTITY entity = get(id).orElse(null);
        repo.deleteById(id);
        return entity;
    }

    @Override
    public List<ENTITY> removeAll(Collection<ID> ids) {
        List<ENTITY> entities = listAll(ids);
        repo.deleteInBatch(entities);
        return entities;
    }

    @Override
    public boolean isExist(ID id) {
        return repo.existsById(id);
    }

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public void flush() {
        repo.flush();
    }
}
