package org.zchzh.refreshconfig.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zchzh.refreshconfig.annotation.RefreshValue;

/**
 * @author zengchzh
 * @date 2021/8/6
 */

@Data
@Component
@RefreshValue
public class TestProp {

    @Value("${test.name}")
    public String name;

    @Value("${test.age:456}")
    public Integer age;

    @Value("${test.address}")
    public String address;
}
