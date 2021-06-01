package org.zchzh.springdatajpa.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zchzh.springdatajpa.entity.UserEntity;
import org.zchzh.springdatajpa.repository.UserRepo;
import org.zchzh.springdatajpa.service.UserService;
import org.zchzh.springdatajpa.types.Username;

import java.util.List;

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
