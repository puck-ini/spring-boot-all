package org.zchzh.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zchzh.test.entity.User;

/**
 * @author zengchzh
 * @date 2022/8/3
 */
public interface UserRepo extends JpaRepository<User, Long> {


    User findByUsername(String username);

    void deleteAllByUsername(String username);
}
