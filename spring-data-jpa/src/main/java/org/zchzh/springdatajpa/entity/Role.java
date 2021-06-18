package org.zchzh.springdatajpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * @author zengchzh
 * @date 2021/6/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Role extends BaseEntity{

    private String name;

    private String description;
}
