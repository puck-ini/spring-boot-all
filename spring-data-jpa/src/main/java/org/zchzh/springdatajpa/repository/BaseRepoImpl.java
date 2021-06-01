package org.zchzh.springdatajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
public class BaseRepoImpl<ENTITY, ID> extends SimpleJpaRepository<ENTITY, ID> implements BaseRepo<ENTITY, ID> {

    private JpaEntityInformation<ENTITY, ID> entityInformation;

    private EntityManager entityManager;

    public BaseRepoImpl(JpaEntityInformation<ENTITY, ID> entityInformation,
                        EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

//    public BaseRepoImpl(Class<ENTITY> domainClass, EntityManager em) {
//        super(domainClass, em);
//        this.entityManager = em;
//    }

    @Override
    public List<ENTITY> findAllById(Collection<ID> ids, Sort sort) {
        if (!ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        if (entityInformation.hasCompositeId()) {
            List<ENTITY> results = new ArrayList<>();
            ids.forEach(id -> super.findById(id).ifPresent(results::add));
            return results;
        }

        ByIdsSpecification<ENTITY> specification = new ByIdsSpecification<>(entityInformation);
        TypedQuery<ENTITY> query = super.getQuery(specification, sort);
        return query.setParameter(specification.parameter, ids).getResultList();
    }

    @Override
    public Page<ENTITY> findAllById(Collection<ID> ids, Pageable pageable) {
        if (!ids.iterator().hasNext()) {
            return new PageImpl<>(Collections.emptyList());
        }

        if (entityInformation.hasCompositeId()) {
            throw new UnsupportedOperationException("Unsupported find all by composite id with page info");
        }

        ByIdsSpecification<ENTITY> specification = new ByIdsSpecification<>(entityInformation);
        TypedQuery<ENTITY> query = super.getQuery(specification, pageable)
                .setParameter(specification.parameter, ids);
        TypedQuery<Long> countQuery = getCountQuery(specification, getDomainClass())
                .setParameter(specification.parameter, ids);

        return pageable.isUnpaged() ?
                new PageImpl<>(query.getResultList())
                : readPage(query, getDomainClass(), pageable, countQuery);
    }

    protected <S extends ENTITY> Page<S> readPage(TypedQuery<S> query,
                                                  Class<S> domainClass,
                                                  Pageable pageable,
                                                  TypedQuery<Long> countQuery) {

        if (pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        return PageableExecutionUtils.getPage(query.getResultList(), pageable,
                () -> executeCountQuery(countQuery));
    }

    private static long executeCountQuery(TypedQuery<Long> query) {

        Assert.notNull(query, "TypedQuery must not be null!");

        List<Long> totals = query.getResultList();
        long total = 0L;

        for (Long element : totals) {
            total += element == null ? 0 : element;
        }

        return total;
    }

    private static final class ByIdsSpecification<T> implements Specification<T> {
        private static final long serialVersionUID = 1L;
        private final JpaEntityInformation<T, ?> entityInformation;
        @Nullable
        ParameterExpression<Collection> parameter;

        ByIdsSpecification(JpaEntityInformation<T, ?> entityInformation) {
            this.entityInformation = entityInformation;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            Path<?> path = root.get(this.entityInformation.getIdAttribute());
            this.parameter = cb.parameter(Collection.class);
            return path.in(this.parameter);
        }
    }
}
