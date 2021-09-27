package org.zchzh.dynamic.datasource.entity.first;

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
public class TestEntity1 {

    @Id
    private String id = UUID.randomUUID().toString();

    private String info;

    public TestEntity1() {
        this.info = "info : " + id;
    }
}
