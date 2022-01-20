package org.zchzh.springdatajpa.repo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.springdatajpa.entity.inherit.ChildOne;
import org.zchzh.springdatajpa.entity.inherit.ChildTwo;
import org.zchzh.springdatajpa.entity.inherit.Parent;
import org.zchzh.springdatajpa.repository.ChildOneRepo;
import org.zchzh.springdatajpa.repository.ChildTwoRepo;
import org.zchzh.springdatajpa.repository.ParentRepo;

/**
 * @author zengchzh
 * @date 2022/1/13
 */


@Slf4j
@SpringBootTest
public class InheritRepoTest {

    @Autowired
    private ParentRepo parentRepo;

    @Autowired
    private ChildOneRepo childOneRepo;

    @Autowired
    private ChildTwoRepo childTwoRepo;


    @Test
    void test() {
        ChildOne c1 = new ChildOne();
        c1.setName("c1");
        c1.setChildOne("c1");
        ChildOne sc1 = parentRepo.save(c1);

        ChildTwo c2 = new ChildTwo();
        c2.setAge(1);
        c2.setName("c2");
        c2.setChildTwo("c2");
        ChildTwo sc2 = parentRepo.save(c2);


    }


    @Test
    void get() {
        Parent c1 = parentRepo.findById(4L).orElse(new Parent());
        log.info(c1.getClass().getSimpleName());

        ChildOne childOne = childOneRepo.findById(4L).orElse(new ChildOne());
        log.info(childOne.getClass().getSimpleName());
    }

    @Test
    void childSave() {
        ChildOne childOne = new ChildOne();
        childOne.setChildOne("c1");
        childOne.setName("c1");
        ChildOne childOne1 = childOneRepo.save(childOne);
    }
}
