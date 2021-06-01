package org.zchzh.mongo.config;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

import javax.annotation.Resource;

/**
 * @author zengchzh
 * @date 2020/12/21
 */

@Configuration
public class MongodbConfig {

    @Resource
    MongoDbFactory mongoDbFactory;

    @Bean
    public GridFSBucket gridFsBucket(){
        MongoDatabase database = mongoDbFactory.getDb();
        return GridFSBuckets.create(database);
    }
}
