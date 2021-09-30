package org.zchzh.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zchzh.querydsl.dto.TestDTO;
import org.zchzh.querydsl.entity.OtherEntity;
import org.zchzh.querydsl.entity.QOtherEntity;
import org.zchzh.querydsl.entity.QTestEntity;
import org.zchzh.querydsl.entity.TestEntity;
import org.zchzh.querydsl.repository.OtherEntityRepo;
import org.zchzh.querydsl.repository.TestEntityRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class QueryDslApplicationTest {

    @Autowired
    private TestEntityRepo testEntityRepo;

    @Autowired
    private OtherEntityRepo otherEntityRepo;

    @Autowired
    private JPAQueryFactory queryFactory;

    private final QTestEntity qTestEntity = QTestEntity.testEntity;

    private final QOtherEntity qOtherEntity = QOtherEntity.otherEntity;

    Long id = 1L;
    Long otherId = 1L;

    @Test
    @BeforeEach
    public void before() {
        TestEntity testEntity = new TestEntity(id, otherId);
        OtherEntity otherEntity = new OtherEntity(otherId);
        testEntityRepo.save(testEntity);
        otherEntityRepo.save(otherEntity);
    }


    @Test
    public void getOne() {
        TestDTO dto = getDtoQuery().where(qTestEntity.id.eq(id)).fetchOne();
        Assert.assertNotNull(dto);
        log.info(dto.toString());
    }

    @Test
    public void list() {
        List<TestDTO> dtoList = getDtoQuery().where(qTestEntity.msg.like("%o%")).fetch();
        Assert.assertNotNull(dtoList);
        for (TestDTO dto : dtoList) {
            log.info(dto.toString());
        }
    }



    private JPAQuery<TestDTO> getDtoQuery() {
        return queryFactory.select(
                Projections.bean(TestDTO.class,
                        qTestEntity.id,
                        qTestEntity.msg,
                        qTestEntity.info,
                        qTestEntity.otherId,
                        qOtherEntity.createTime,
                        qOtherEntity.updateTime)
        ).from(qTestEntity).leftJoin(qOtherEntity).on(qTestEntity.otherId.eq(qOtherEntity.id));
    }

}