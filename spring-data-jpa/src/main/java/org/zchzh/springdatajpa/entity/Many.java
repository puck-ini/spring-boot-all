package org.zchzh.springdatajpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/6/27
 */

@Entity
@Data
public class Many {

    @Id
    private Long id;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    // 不生成外键
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private One one;
}
