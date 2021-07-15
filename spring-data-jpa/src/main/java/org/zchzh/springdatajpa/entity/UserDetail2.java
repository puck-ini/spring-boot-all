package org.zchzh.springdatajpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/7/15
 */
@Data
@Entity
public class UserDetail2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String address;
}
