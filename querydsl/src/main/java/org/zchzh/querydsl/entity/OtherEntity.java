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
public class OtherEntity {

    @Id
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public OtherEntity() {}

    public OtherEntity(Long id) {
        this.id = id;
        createTime = updateTime = LocalDateTime.now();
    }

}
