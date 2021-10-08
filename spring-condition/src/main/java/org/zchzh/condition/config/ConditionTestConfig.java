package org.zchzh.condition.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zchzh.condition.test.DefaultPrint;
import org.zchzh.condition.test.HelloPrint;
import org.zchzh.condition.test.Print;
import org.zchzh.condition.test.TestPrint;

import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/10/8
 */

@Slf4j
@Configuration
public class ConditionTestConfig {


    /**
     * 当 condition.type 配置存在且值为 hello 时执行
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "condition", name = "type", havingValue = "hello")
    public Print helloPrint() {
        Print print = new HelloPrint();
        print.print();
        return print;
    }

    /**
     * 当 condition.type 配置存在且值为 test 时执行
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "condition", name = "type", havingValue = "test")
    public Print testPrint() {
        Print print = new TestPrint();
        print.print();
        return print;
    }


    /**
     * 当不存在 Print bean 时执行
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public Print defaultPrint() {
        Print print = new DefaultPrint();
        print.print();
        return print;
    }

    /**
     * 当存在 Print bean 时执行
     * @return
     */
    @Bean
    @ConditionalOnBean
    public Print defaultPrint2() {
        log.info("ConditionalOnBean " + LocalDateTime.now());
        Print print = new DefaultPrint();
        print.print();
        return print;
    }
}
