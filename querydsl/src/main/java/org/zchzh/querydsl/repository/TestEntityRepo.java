package org.zchzh.querydsl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zchzh.querydsl.entity.TestEntity;

/**
 * @author zengchzh
 * @date 2021/9/29
 */
public interface TestEntityRepo extends JpaRepository<TestEntity, Long> {
}
