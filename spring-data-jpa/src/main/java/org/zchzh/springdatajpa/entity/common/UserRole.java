package org.zchzh.springdatajpa.entity.common;

import lombok.Data;
import org.zchzh.springdatajpa.types.UserRoleId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * @author zengchzh
 * @date 2021/6/18
 */

@Data
@Entity
public class UserRole {

    @EmbeddedId
    private UserRoleId userRoleId;
}
