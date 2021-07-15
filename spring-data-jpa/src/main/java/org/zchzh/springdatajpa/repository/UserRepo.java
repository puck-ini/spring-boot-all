package org.zchzh.springdatajpa.repository;



import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.zchzh.springdatajpa.entity.UserEntity;
import org.zchzh.springdatajpa.types.Username;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/4
 */
public interface UserRepo extends BaseRepo<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /**
     * 通过名字获取
     * @param username
     * @return
     */
    List<UserEntity> findAllByUsername(Username username);

    /**
     * 返回DTO TODO org.springframework.core.convert.ConverterNotFoundException: No converter found capable of converting from type [com.zchzh.jpa.entity.UserEntity] to type [com.zchzh.jpa.dto.UserDTO]
     * @param username
     * @param type DTO.class
     * @param <T> DTO
     * @return
     */
    <T> List<T> findAllByUsername(Username username, Class<T> type);
}
