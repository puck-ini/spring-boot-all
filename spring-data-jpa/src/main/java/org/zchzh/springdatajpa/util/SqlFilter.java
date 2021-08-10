package org.zchzh.springdatajpa.util;


import org.zchzh.springdatajpa.enums.SqlConnectEnum;
import org.zchzh.springdatajpa.enums.SqlOperateEnum;

import java.io.Serializable;

/**
 * @author zengchzh
 * @date 2021/8/5
 */
public class SqlFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    private String property;
    private Object value;
    private SqlConnectEnum sqlConnectEnum;
    private SqlOperateEnum sqlOperateEnum;



    public SqlFilter() {
    }

    public SqlFilter(SqlConnectEnum sqlConnectEnum, String property, SqlOperateEnum sqlOperateEnum, Object value) {
        this.sqlConnectEnum = sqlConnectEnum;
        this.property = property;
        this.sqlOperateEnum = sqlOperateEnum;
        this.value = value;

    }

    public static SqlFilter andEq(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.EQ, value);
    }
    public static SqlFilter orEq(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.EQ, value);
    }

    public static SqlFilter andNe(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.NE, value);
    }
    public static SqlFilter orNe(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.NE, value);
    }

    public static SqlFilter andGt(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.GT, value);
    }
    public static SqlFilter orGt(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.GT, value);
    }

    public static SqlFilter andLt(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.LT, value);
    }
    public static SqlFilter orLt(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.LT, value);
    }

    public static SqlFilter andGe(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.GE, value);
    }
    public static SqlFilter orGe(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.GE, value);
    }

    public static SqlFilter andLe(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.LE, value);
    }
    public static SqlFilter orLe(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.LE, value);
    }

    public static SqlFilter andLike(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.LIKE, value);
    }
    public static SqlFilter orLike(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.LIKE, value);
    }

    public static SqlFilter andIn(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.IN, value);
    }
    public static SqlFilter orIn(String property, Object value) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.IN, value);
    }

    public static SqlFilter andIsNull(String property) {
        return new SqlFilter(SqlConnectEnum.AND, property, SqlOperateEnum.ISNULL, (Object)null);
    }
    public static SqlFilter orIsNull(String property) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.ISNULL, (Object)null);
    }

    public static SqlFilter andIsNotNull(String property) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.ISNOTNULL, (Object)null);
    }
    public static SqlFilter orIsNotNull(String property) {
        return new SqlFilter(SqlConnectEnum.OR, property, SqlOperateEnum.ISNOTNULL, (Object)null);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SqlConnectEnum getSqlConnectEnum() {
        return sqlConnectEnum;
    }

    public void setSqlConnectEnum(SqlConnectEnum sqlConnectEnum) {
        this.sqlConnectEnum = sqlConnectEnum;
    }

    public SqlOperateEnum getSqlOperateEnum() {
        return sqlOperateEnum;
    }

    public void setSqlOperateEnum(SqlOperateEnum sqlOperateEnum) {
        this.sqlOperateEnum = sqlOperateEnum;
    }
}
