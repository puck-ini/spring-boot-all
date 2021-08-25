package org.zchzh.transactional.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zchzh.transactional.entity.TestEntity;

/**
 * @author zengchzh
 * @date 2021/8/23
 */
public interface TestRepo extends JpaRepository<TestEntity, Long> {
}
