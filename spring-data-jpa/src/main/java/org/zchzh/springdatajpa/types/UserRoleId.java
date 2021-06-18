package org.zchzh.springdatajpa.types;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/6/18
 */

@Embeddable
@Data
public class UserRoleId implements Serializable {

    private Long userId;

    private Long roleId;
}
