package org.zchzh.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zchzh.test.convert.UserConvert;
import org.zchzh.test.dto.UserDTO;
import org.zchzh.test.entity.User;
import org.zchzh.test.repo.UserRepo;
import org.zchzh.test.request.CreateReq;
import org.zchzh.test.request.LoginReq;
import org.zchzh.test.service.CacheService;
import org.zchzh.test.service.UserService;
import org.zchzh.test.service.VerifyCodeService;

import java.util.Objects;

/**
 * @author zengchzh
 * @date 2022/8/3
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private CacheService cacheService;


    private static final int MAX_FAIL_LIMIT = 5;

    @Override
    public UserDTO login(LoginReq req) {
        req.check();

        String key = req.getUsername();
        Object value = cacheService.get(key);
        if (Objects.nonNull(value)) {
            Integer count = (Integer) value;
            if (count >= MAX_FAIL_LIMIT) {
                throw new IllegalArgumentException("当前用户已被锁定");
            }
        }

        if (!verifyCodeService.verify(req.getCode())) {
            throw new IllegalArgumentException("验证码错误");
        }

        User user = userRepo.findByUsername(req.getUsername());
        if (Objects.isNull(user) || !Objects.equals(user.getPassword(), req.getPassword())) {
            Integer count = 1;
            if (Objects.nonNull(value)) {
                count = (Integer) value;
                count++;
            }
            cacheService.set(key, count);
            throw new IllegalArgumentException("用户名或密码错误");
        }
        cacheService.remove(key);
        return UserConvert.CONVERT.toDTO(user);
    }

    @Override
    public UserDTO create(CreateReq req) {
        req.check();
        User user = UserConvert.CONVERT.toEntity(req);
        return UserConvert.CONVERT.toDTO(userRepo.save(user));
    }
}
