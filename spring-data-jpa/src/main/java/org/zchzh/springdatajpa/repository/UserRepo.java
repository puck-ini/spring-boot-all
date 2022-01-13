package org.zchzh.springdatajpa.repository;



import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.zchzh.springdatajpa.entity.common.UserEntity;
import org.zchzh.springdatajpa.types.Username;

import java.util.List;
import java.util.Map;

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

    /**
     * 自定义 sql语句示例, 使用 hql 无法返回其他表的数据（没有使用 @OneToOne 等注解的表）
     * 原生 sql 返回自定义对象可以使用 List<Map<String, Object>> 去接收, 之后使用 json 工具转成 json 字符串再转成自定义的对象
     * @param userId
     * @return
     */
//    @Query("select new org.zchzh.springdatajpa.dto.UserDTO(a.id, a.username.username, a.password.password, b.name, b.age)" +
//            "from UserEntity as a left join UserDetail2 as b on a.userDetail2Id = b.id where a.id=?1")
//    List<UserDTO> findByUserId(Long userId);
    @Query(value ="select a.id, a.username, a.password, a.user_type, b.name, b.age " +
            "from user_entity a left join user_detail2 b on a.user_detail2id = b.id where a.id = ?1", nativeQuery = true)
    List<Map<String, Object>> findByUserId(Long userId);
}
