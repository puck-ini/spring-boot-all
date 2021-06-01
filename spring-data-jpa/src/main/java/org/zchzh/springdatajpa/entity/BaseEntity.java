package org.zchzh.springdatajpa.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zengchzh
 * @date 2021/5/4
 */

@ToString
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * jpa version 乐观锁，解决并发更新的问题
     */
    @Version
    private Integer version;


    @Column
    private boolean deleted;

    /**
     * 在实体保存到数据库之前执行的操作
     */
    @PrePersist
    public void prePersist(){
        this.createTime = this.updateTime = new Date();
    }

    /**
     * 在实体更新到数据库之前执行的操作
     */
    @PreUpdate
    public void preUpdate(){
        this.updateTime = new Date();
    }

    /**
     * 在实体从数据库删除之前执行的操作
     */
    @PreRemove
    public void preRemove(){
        this.updateTime = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
