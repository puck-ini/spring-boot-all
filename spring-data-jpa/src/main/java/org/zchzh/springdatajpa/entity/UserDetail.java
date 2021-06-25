package org.zchzh.springdatajpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/18
 */

@Data
@Entity
public class UserDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
//    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private String name;

    private Integer age;

    private String address;


}
