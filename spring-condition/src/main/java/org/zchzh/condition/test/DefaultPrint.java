package org.zchzh.condition.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zengchzh
 * @date 2021/10/8
 */
@Slf4j
public class DefaultPrint implements Print {
    @Override
    public void print() {
        log.info("default");
    }
}
