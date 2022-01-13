package org.zchzh.springdatajpa.service;


import org.springframework.data.domain.Page;
import org.zchzh.springdatajpa.entity.common.UserEntity;
import org.zchzh.springdatajpa.request.SearchReq;
import org.zchzh.springdatajpa.types.Username;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/11
 */
public interface UserService extends BaseCrudService<UserEntity, Long> {


    /**
     * 复杂查询
     * @param req 查询参数
     * @return 返回分页数据
     */
    Page<UserEntity> search(SearchReq req);


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
