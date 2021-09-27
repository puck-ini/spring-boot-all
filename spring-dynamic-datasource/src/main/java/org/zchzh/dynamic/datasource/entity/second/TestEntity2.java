package org.zchzh.dynamic.datasource.entity.second;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author zengchzh
 * @date 2021/9/27
 */

@Data
@Entity
public class TestEntity2 {

    @Id
    private String id = UUID.randomUUID().toString();

    private String info;

    public TestEntity2() {
        this.info = "info : " + id;
    }
}
