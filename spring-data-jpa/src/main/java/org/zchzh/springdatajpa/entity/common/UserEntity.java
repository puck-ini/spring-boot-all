package org.zchzh.springdatajpa.entity.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Where;
import org.zchzh.springdatajpa.entity.BaseEntity;
import org.zchzh.springdatajpa.types.Password;
import org.zchzh.springdatajpa.types.UserType;
import org.zchzh.springdatajpa.types.Username;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/5/4
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
@Where(clause = "deleted = false")
public class UserEntity extends BaseEntity<UserEntity> {

    /**
     * 用户名
     */
    private Username username;

    /**
     * 密码
     */
    private Password password;
    /**
     * 用户类型
     */
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserDetail userDetail;

    private Long userDetail2Id;

    public UserEntity() {
        this.userType = UserType.USER;
    }

    private UserEntity(UserType userType) {
        this.userType = userType;
    }

    public static UserEntity newAdmin() {
        return new UserEntity(UserType.ADMIN);
    }

    public static UserEntity newUser() {
        return new UserEntity(UserType.USER);
    }

    public UserEntity username(String username) {
        this.username = new Username(username);
        return this;
    }

    public UserEntity password(String password) {
        this.password = new Password(password);
        return this;
    }

    public UserEntity userType(String userType) {
        this.userType = UserType.valueOf(userType);
        return this;
    }

    public void setUsername(String username) {
        this.username(username);
    }

    public void setPassword(String password) {
        this.password(password);
    }
}
