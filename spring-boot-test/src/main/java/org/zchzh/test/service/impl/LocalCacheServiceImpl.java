package org.zchzh.test.service.impl;

import cn.hutool.cache.impl.LRUCache;
import org.springframework.stereotype.Service;
import org.zchzh.test.service.CacheService;

import java.util.Objects;

/**
 * @author zengchzh
 * @date 2022/8/4
 */

@Service
public class LocalCacheServiceImpl implements CacheService {

    private final LRUCache<String, Object> cache = new LRUCache<>(100, 30000);

    @Override
    public void set(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }
}
