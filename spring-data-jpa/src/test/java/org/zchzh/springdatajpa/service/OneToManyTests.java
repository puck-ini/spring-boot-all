package org.zchzh.springdatajpa.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.springdatajpa.entity.onemany.Many;
import org.zchzh.springdatajpa.entity.onemany.One;
import org.zchzh.springdatajpa.repository.ManyRepo;
import org.zchzh.springdatajpa.repository.OneRepo;
import org.zchzh.springdatajpa.service.impl.OneService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/12/10
 */

@Slf4j
@SpringBootTest
public class OneToManyTests {

    @Autowired
    private OneRepo oneRepo;

    @Autowired
    private ManyRepo manyRepo;

    private static final long ONE_ID = 1L;
//    @BeforeEach
    @Test
    void init() {
        One one = new One();
        one.setId(ONE_ID);
        List<Many> manyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Many many = new Many();
            many.setId((long) i);
            many.setOne(one);
            manyList.add(many);
        }
        one.setManyList(manyList);
        oneRepo.save(one);
    }


    @Test
    void getOne() {
        One one = oneRepo.findById(ONE_ID).orElse(new One());
        log.info(one.toString());
        for (Many many : one.getManyList()) {
            log.info(many.toString());
        }
    }

    @Test
    void getMany() {
        Many many = manyRepo.findById(0L).orElse(new Many());
        log.info(many.toString());
    }


    @Test
    void addMany() {
        One one = oneRepo.findById(ONE_ID).orElse(new One());
        Many many = new Many();
        many.setOne(one);
        many.setId(999L);
        one.getManyList().add(many);
        oneRepo.save(one);
        for (Many m : oneRepo.findById(ONE_ID).orElse(new One()).getManyList()) {
            log.info(m.toString());
        }
    }

    @Test
    void addManyOne() {
        One one = new One();
        one.setId(999L);
        Many many = new Many();
        many.setOne(one);
        many.setId(9990L);
        one.getManyList().add(many);
        manyRepo.saveAndFlush(many);

        One one1 = oneRepo.findById(999L).orElse(new One());
        log.info(one1.toString());
        for (Many m : one1.getManyList()) {
            log.info(m.toString());
        }
    }

//    @AfterEach
    @Test
    void deleteOne() {
        oneRepo.deleteById(ONE_ID);
    }


    @Autowired
    private OneService oneService;

    @Test
    void deleteMany() {
//        manyRepo.deleteAll();
//        manyRepo.deleteById(0L);
//        oneService.deleteMany();
    }



}
