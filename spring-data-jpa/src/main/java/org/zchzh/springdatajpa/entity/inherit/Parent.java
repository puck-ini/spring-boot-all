package org.zchzh.springdatajpa.entity.inherit;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2022/1/13
 */

@ToString
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
