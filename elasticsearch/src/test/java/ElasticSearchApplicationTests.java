import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.zchzh.elasticsearch.ElasticSearchApplication;
import org.zchzh.elasticsearch.entity.DemoUser;
import org.zchzh.elasticsearch.repositry.DemoUserRepo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/8/18
 */

@Slf4j
@SpringBootTest(classes = ElasticSearchApplication.class)
public class ElasticSearchApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate template;

    @Autowired
    private DemoUserRepo repo;


    @Test
    public void insert() {
        DemoUser user = DemoUser.builder()
                .id(1123L)
                .age(123)
                .createTime(new Date())
                .decimal(new BigDecimal("123.321"))
                .desc("eqrwqe13测试测试")
                .name("测试")
                .num(12321d)
                .username("123adasda(_)(_)(^&*&()")
                .build();
        repo.save(user);
    }


    @Test
    public void list() {
        for (DemoUser user : repo.findAll()) {
            log.info(user.toString());
        }
    }


    @Test
    public void delete() {
        for (DemoUser user : repo.findAll()) {
            log.info("delete : " + user.toString());
            repo.delete(user);
        }
    }
}
