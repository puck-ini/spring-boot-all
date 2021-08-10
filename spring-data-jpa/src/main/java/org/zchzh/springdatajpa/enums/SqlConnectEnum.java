package org.zchzh.springdatajpa.enums;

/**
 * @author zengchzh
 * @date 2021/8/5
 */
public enum  SqlConnectEnum {
    /**
     * sql 链接属性 and
     */
    AND,

    /**
     * sql 链接属性 or
     */
    OR;

    SqlConnectEnum() {
    }

    public static SqlConnectEnum fromString(String value) {
        return valueOf(value.toLowerCase());
    }
}
