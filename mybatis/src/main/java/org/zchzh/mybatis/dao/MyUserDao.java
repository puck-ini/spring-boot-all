package org.zchzh.mybatis.dao;

import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;
import org.zchzh.mybatis.entity.MyUser;

import java.util.List;

/**
 * @author zengchzh
 * @date 2023/8/9
 */
@Repository
public interface MyUserDao {


    void insertBatchV1(@Param("myUserList") List<MyUser> myUserList);


    void insertBatchV2(@Param("myUserList") List<MyUser> myUserList);

    void insertBatchV3(@Param("myUserList") List<MyUser> myUserList);

    void insertBatchV4(@Param("myUserList") List<MyUser> myUserList);
}
