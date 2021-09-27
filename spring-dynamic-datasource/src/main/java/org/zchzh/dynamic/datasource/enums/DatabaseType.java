package org.zchzh.dynamic.datasource.enums;

/**
 * @author zengchzh
 * @date 2021/6/6
 */
public enum DatabaseType {
    /**
     * mysql
     */
    MYSQL("mysql"),
    /**
     * pg
     */
    POSTGRES("postgresql"),
    /**
     * oracle
     */
    ORACLE("oracle");

    private final String name;

    DatabaseType(String name) {
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}
