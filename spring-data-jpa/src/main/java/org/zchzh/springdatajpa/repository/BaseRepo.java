package org.zchzh.springdatajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
@NoRepositoryBean
public interface BaseRepo<ENTITY, ID> extends JpaRepository<ENTITY, ID> {


    List<ENTITY> findAllById(Collection<ID> ids, Sort sort);

    Page<ENTITY> findAllById(Collection<ID> ids, Pageable pageable);
}
