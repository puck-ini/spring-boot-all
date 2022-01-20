package org.zchzh.springdatajpa.entity.inherit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author zengchzh
 * @date 2022/1/13
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class ChildOne extends Parent {

    private String childOne;


}
