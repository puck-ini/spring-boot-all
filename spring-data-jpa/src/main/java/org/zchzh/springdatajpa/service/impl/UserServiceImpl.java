package org.zchzh.springdatajpa.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zchzh.springdatajpa.entity.UserDetail;
import org.zchzh.springdatajpa.entity.UserEntity;
import org.zchzh.springdatajpa.repository.UserRepo;
import org.zchzh.springdatajpa.request.SearchReq;
import org.zchzh.springdatajpa.service.UserService;
import org.zchzh.springdatajpa.types.Username;
import org.zchzh.springdatajpa.util.SqlFilter;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
@Service
public class UserServiceImpl extends AbstractCrudService<UserEntity, Long> implements UserService {


    private final UserRepo userRepo;

    protected UserServiceImpl(UserRepo userRepo) {
        super(userRepo);
        this.userRepo = userRepo;
    }

    @Override
    public Page<UserEntity> search(SearchReq req) {
        Specification<UserEntity> specification = new Specification<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
//                if (Objects.nonNull(req.getName())) {
//                    predicateList.add(cb.like(root.get("username").as(String.class), "%" + req.getName() + "%"));
//                }
                if (Objects.nonNull(req.getStart())) {
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), req.getStart()));
                }
                if (Objects.nonNull(req.getEnd())) {
                    predicateList.add(cb.lessThanOrEqualTo(root.get("createTime"), req.getEnd()));
                }
                if (Objects.nonNull(req.getName())) {
                    // 多表关联，依赖于@OneToOne
                    Join<UserEntity, UserDetail> join = root.join("userDetail", JoinType.INNER);
                    predicateList.add(cb.like(join.get("name").as(String.class), "%" + req.getName() + "%"));
                    // 根据字段排序
                    query.orderBy(cb.desc(join.get("age").as(Integer.class)));
                }

                Predicate[] predicateArr = new Predicate[predicateList.size()];
                predicateList.toArray(predicateArr);
                query.where(cb.and(predicateArr));
                return query.getRestriction();
            }
        };
//        List<SqlFilter> sqlFilters = new ArrayList<>();
//        sqlFilters.add(SqlFilter.andGe("createTime", req.getStart()));
//        sqlFilters.add(SqlFilter.andLe("createTime", req.getEnd()));
//        UserEntity entity = new UserEntity();
//        return userRepo.findAll(new UserEntity().toSpecification(sqlFilters), PageRequest.of(req.getPageNum(), req.getPageSize()));
        return userRepo.findAll(specification, PageRequest.of(req.getPageNum(), req.getPageSize()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<UserEntity> findAllByUsername(Username username) {
        List<UserEntity> userEntityList = userRepo.findAllByUsername(username);
        return userRepo.findAllByUsername(username);
    }

    @Override
    public <T> List<T> findAllByUsername(Username username, Class<T> type) {
        return userRepo.findAllByUsername(username, type);
    }
}
