package org.zchzh.mongo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.zchzh.mongo.entity.TestData;

import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/1
 */

@Slf4j
@SpringBootTest
public class MongoApplicationTests {

//    @Autowired
//    private MongoTemplate mongoTemplateTemp;
//
//
//    @Test
//    void insert() {
//        TestData testData = new TestData("1","test1","test1");
//        for (int i = 11; i <= 20; i++){
//            mongoTemplateTemp.save(new TestData(String.valueOf(i),
//                    "test" + i,
//                    "test" + i));
//        }
//
//    }
//
//    @Test
//    void find() {
//        List<TestData> testDataList = mongoTemplateTemp.findAll(TestData.class);
//        testDataList.forEach(i -> log.info(i.toString()));
//    }
}
