package org.zchzh.dynamic.datasource.repository.firstds;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.dynamic.datasource.entity.first.Test1;
import org.zchzh.dynamic.datasource.entity.first.TestEntity1;
import org.zchzh.dynamic.datasource.entity.second.Test2;
import org.zchzh.dynamic.datasource.entity.second.TestEntity2;
import org.zchzh.dynamic.datasource.repository.secondds.Test2Repo;
import org.zchzh.dynamic.datasource.repository.secondds.TestEntity2Repo;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class Test1RepoTest {

    @Autowired
    private Test1Repo test1Repo;

    @Autowired
    private TestEntity1Repo testEntity1Repo;

    @Autowired
    private Test2Repo test2Repo;

    @Autowired
    private TestEntity2Repo testEntity2Repo;

    @Test
    @BeforeEach
    public void create() {
        test1Repo.save(new Test1());
        testEntity1Repo.save(new TestEntity1());
        test2Repo.save(new Test2());
        testEntity2Repo.save(new TestEntity2());
    }


    @Test
    public void get() {
        test1Repo.findAll().forEach(i -> log.info(i.toString()));
        testEntity1Repo.findAll().forEach(i -> log.info(i.toString()));
        test2Repo.findAll().forEach(i -> log.info(i.toString()));
        testEntity2Repo.findAll().forEach(i -> log.info(i.toString()));
    }

}