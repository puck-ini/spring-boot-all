package org.zchzh.querydsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author zengchzh
 * @date 2021/9/29
 */

@Entity
@Data
public class TestEntity {
    @Id
    private Long id;

    private String msg;

    private String info;

    private Long otherId;

    public TestEntity() {}

    public TestEntity(Long id, Long otherId) {
        this.id = id;
        this.msg = "hello world";
        this.info = LocalDateTime.now().toString();
        this.otherId = otherId;
    }
}
