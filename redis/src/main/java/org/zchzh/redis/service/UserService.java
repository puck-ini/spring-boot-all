package org.zchzh.redis.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.zchzh.redis.entity.User;

import java.util.*;

/**
 * @author zengchzh
 * @date 2021/5/27
 */

@CacheConfig(cacheNames = "user")
@Service
public class UserService {

    private final List<User> userList = new ArrayList<>();

    private final Random random = new Random();

    @Cacheable(cacheNames = "getAll")
    public List<User> getAll() {

        for (int i = 0; i< 10; i++) {
            User user = User.builder()
                    .id((long) i)
                    .username("username" + i)
                    .password("password" + i)
                    .age(i)
                    .name("name" + i)
                    .build();
            userList.add(user);
        }
        return userList;
    }

    @Cacheable(cacheNames = "getOne", key = "#p0")
    public User getOne(Long id) {
        for (User user : userList) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }

    @CachePut(cacheNames = "getAll", key = "#p0")
    public User create(User user) {
        userList.add(user);
        return user;
    }

    @CacheEvict(cacheNames = "getAll",  allEntries = true)
    public void remove(Long id) {
        userList.removeIf(user -> Objects.equals(user.getId(), id));
    }

    @CachePut(cacheNames = "getOne", key = "#p0.id")
    public User update(User user) {
        for (User user1 : userList) {
            if (Objects.equals(user.getId(), user1.getId())) {
                user1 = user;
                return user1;
            }
        }
        return null;
    }


}
