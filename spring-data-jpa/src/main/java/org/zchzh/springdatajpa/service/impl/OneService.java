package org.zchzh.springdatajpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zchzh.springdatajpa.entity.onemany.Many;
import org.zchzh.springdatajpa.entity.onemany.One;
import org.zchzh.springdatajpa.repository.ManyRepo;
import org.zchzh.springdatajpa.repository.OneRepo;

/**
 * @author zengchzh
 * @date 2021/12/24
 */

@Service
public class OneService {

    @Autowired
    private OneRepo oneRepo;

    @Autowired
    private ManyRepo manyRepo;

    @Transactional
    public void deleteMany() {
        Many many = manyRepo.getOne(0L);
        One one = oneRepo.getOne(many.getOne().getId());
        one.getManyList().remove(many);
        oneRepo.save(one);
        manyRepo.deleteById(0L);
    }
}
