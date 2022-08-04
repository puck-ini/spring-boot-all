package org.zchzh.test.service;

/**
 * @author zengchzh
 * @date 2022/8/4
 */
public interface CacheService {

    void set(String key, Object value);

    Object get(String key);

    void remove(String key);
}
