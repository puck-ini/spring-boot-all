package org.zchzh.elasticsearch.repositry;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.zchzh.elasticsearch.entity.DemoUser;

/**
 * @author zengchzh
 * @date 2021/8/18
 */
public interface DemoUserRepo extends ElasticsearchRepository<DemoUser, Long> {
}
