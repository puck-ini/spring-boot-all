package org.zchzh.springdatajpa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
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
    // 解决 lombok 生成的toString 方法循环调用问题
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "one", fetch = FetchType.EAGER)
    private List<Many> manyList;
}
