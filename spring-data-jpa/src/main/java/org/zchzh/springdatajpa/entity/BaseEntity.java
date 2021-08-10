package org.zchzh.springdatajpa.entity;

import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.zchzh.springdatajpa.enums.SqlConnectEnum;
import org.zchzh.springdatajpa.util.SqlFilter;
import org.zchzh.springdatajpa.util.SqlUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/5/4
 */

@ToString
@MappedSuperclass
public class BaseEntity<T> {

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

    /**
     * 获取按实体参数进行查询的specification
     * @Param 无
     * return specification
     */
    public Specification<T> toSpecification() {
        return getSpecification();
    }

    /**
     * 获取按过滤参数进行查询的specification
     * @Param 无
     * return specification
     */
    public Specification<T> toSpecification(SqlFilter sqlFilter) {
        List<SqlFilter> list = Arrays.asList(sqlFilter);
        return toSpecification(list);
    }

    /**
     * 获取按过滤参数进行查询的specification
     * @Param 无
     * return specification
     */
    public Specification<T> toSpecification(List<SqlFilter> sqlFilters) {
        return getSpecificationBySqlFilter(sqlFilters);
    }

    /**
     * 根据sqlFilters封装Specification
     * @Param basePo
     * @Param fields
     * return 处理结果
     */
    private Specification<T> getSpecificationBySqlFilter(List<SqlFilter> sqlFilters) {
        // sql过滤为空，执行无过滤查询
        if (CollectionUtils.isEmpty(sqlFilters)){
            return toSpecification();
        }
        BaseEntity basePO = this;
        List<Field> fields = SqlUtils.getAllField(basePO.getClass());
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            // 定义实体部分数组
            List<Predicate> tempPredicateList = new ArrayList();
            // 定义and或者or数组
            List<Predicate> andList = new ArrayList();
            List<Predicate> orList = new ArrayList();
            // 过滤掉自定义参数字段
            List<Field> filterField = SqlUtils.getFilterField(fields, sqlFilters);
            // 循环未过滤参数
//            for (Field field : filterField) {
//                try {
//                    field.setAccessible(true);
//                    Path<Object> path = root.get(field.getName());
//                    // 获得属性的值
//                    Object value = field.get(basePO);
//                    if (value == null) {
//                        continue;
//                    }
//                    tempPredicateList.add(criteriaBuilder.equal(path, value));
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
            // 实体参数默认and链接
            andList.add(criteriaBuilder.and(tempPredicateList.toArray(new Predicate[0])));

            // 循环过滤参数
            for (SqlFilter sqlFilter : sqlFilters) {
                Path<Object> path = root.get(sqlFilter.getProperty());
                // 获得属性的值
                Object value = sqlFilter.getValue();
                if (value == null) {
                    continue;
                }
                // TODO 判断sql查询符未全部写完
                switch(sqlFilter.getSqlOperateEnum()){
                    case EQ:
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.AND){
                            andList.add(criteriaBuilder.and(criteriaBuilder.equal(path, value)));
                        }
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.OR){
                            orList.add(criteriaBuilder.or(criteriaBuilder.equal(path, value)));
                        }
                        break;
                    case NE:
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.AND){
                            andList.add(criteriaBuilder.and(criteriaBuilder.notEqual(path, value)));
                        }
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.OR){
                            orList.add(criteriaBuilder.or(criteriaBuilder.notEqual(path, value)));
                        }
                        break;
                    case IN:
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.AND){
                            CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
                            if(value instanceof List<?>){
                                for (Object o : (List<?>) value) {
                                    in.value(o);
                                }
                            }
                            andList.add(criteriaBuilder.and(in));
                        }
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.OR){
                            CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
                            if(value instanceof List<?>){
                                for (Object o : (List<?>) value) {
                                    in.value(o);
                                }
                            }
                            orList.add(criteriaBuilder.or(in));
                        }
                        break;
                    case LIKE:
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.AND){
                            andList.add(criteriaBuilder.and(criteriaBuilder.like(path.as(String.class), "%" + value + "%")));
                        }
                        if (sqlFilter.getSqlConnectEnum() == SqlConnectEnum.OR){
                            orList.add(criteriaBuilder.or(criteriaBuilder.like(path.as(String.class), "%" + value + "%")));
                        }
                        break;
                    default:
                }
            }
            // 转换成predicate
            Predicate andPredicate = criteriaBuilder.and(andList.toArray(new Predicate[0]));
            Predicate orPredicate = criteriaBuilder.or(orList.toArray(new Predicate[0]));
            return criteriaQuery.where(andPredicate,orPredicate).getRestriction();
        };
    }

    /**
     * 根据fieds反射封装Specification
     * @Param basePo
     * @Param fields
     * return 处理结果
     */
    private Specification<T> getSpecification() {
        BaseEntity basePO = this;
        List<Field> fields = SqlUtils.getAllField(basePO.getClass());
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Path<Object> path = root.get(field.getName());
                    // 获得属性的值
                    Object value = field.get(basePO);
                    if (value == null) {
                        continue;
                    }
                    predicates.add(criteriaBuilder.equal(path, value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            // and连接
            Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            return predicate;
        };
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
