package org.zchzh.springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zchzh.springdatajpa.entity.Many;

/**
 * @author zengchzh
 * @date 2021/12/10
 */
public interface ManyRepo extends JpaRepository<Many, Long> {
}
