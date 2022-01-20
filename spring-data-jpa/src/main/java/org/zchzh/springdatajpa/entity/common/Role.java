package org.zchzh.springdatajpa.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.zchzh.springdatajpa.entity.BaseEntity;

import javax.persistence.Entity;

/**
 * @author zengchzh
 * @date 2021/6/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Role extends BaseEntity {

    private String name;

    private String description;
}
