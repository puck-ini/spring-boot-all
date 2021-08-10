package org.zchzh.springdatajpa.enums;

/**
 * @author zengchzh
 * @date 2021/8/5
 */
public enum SqlOperateEnum {
    /**
     * sql 条件属性 =
     */
    EQ,

    /**
     * sql 条件属性 !=
     */
    NE,

    /**
     * sql 条件属性 >
     */
    GT,
    /**
     * sql 条件属性 <
     */
    LT,
    /**
     * sql 条件属性 =
     */
    GE,
    /**
     * sql 条件属性 =
     */
    LE,
    /**
     * sql 条件属性 like
     */
    LIKE,
    /**
     * sql 条件属性 in
     */
    IN,
    /**
     * sql 条件属性 为空
     */
    ISNULL,
    /**
     * sql 条件属性 不为空
     */
    ISNOTNULL;

    SqlOperateEnum() {
    }

    public static SqlOperateEnum fromString(String value) {
        return valueOf(value.toLowerCase());

    }
}
