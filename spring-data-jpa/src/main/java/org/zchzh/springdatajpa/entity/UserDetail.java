package org.zchzh.springdatajpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/18
 */

@Data
@Entity
public class UserDetail implements Serializable {

    @Id
    @OneToOne
//    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private String name;

    private Integer age;

    private String address;


}
