package org.zchzh.springdatajpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/6/27
 */
@Entity
@Data
public class One {
    @Id
    private Long id;

    @Version
    private int version;

    // 解决 lombok 生成的toString 方法循环调用问题
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @Transient
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), name = "one_id")
    private List<Many> manyList = new ArrayList<>();
}
