package org.zchzh.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zengchzh
 * @date 2021/1/9
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestData {

    private String id;

    private String name;

    private String description;
}
