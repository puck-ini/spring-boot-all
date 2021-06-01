package org.zchzh.springdatajpa.service;


import org.zchzh.springdatajpa.entity.UserEntity;
import org.zchzh.springdatajpa.types.Username;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
public interface UserService extends BaseCrudService<UserEntity, Long> {


    /**
     * 通过名字获取
     * @param username
     * @return
     */
    List<UserEntity> findAllByUsername(Username username);

    /**
     * 返回DTO
     * @param <T>
     * @param username
     * @param type
     * @return
     */
    <T> List<T> findAllByUsername(Username username, Class<T> type);
}
