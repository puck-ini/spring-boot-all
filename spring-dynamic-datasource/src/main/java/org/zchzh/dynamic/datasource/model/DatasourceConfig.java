package org.zchzh.dynamic.datasource.model;

import lombok.Data;
import org.zchzh.dynamic.datasource.enums.DatabaseType;

import javax.persistence.*;

/**
 * @author zengchzh
 * @date 2021/6/6
 */

@Data
public class DatasourceConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 数据库地址
     */
    private String host;

    /**
     * 数据库端口
     */
    private Integer port;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 数据库名称
     */
    private String database;

    /**
     * 数据库类型
     */
    @Enumerated(EnumType.STRING)
    private DatabaseType databaseType;


    public String buildJdbcUrl() {
        return String.format("jdbc:%s://%s:%s/%s?useUnicode=true&characterEncoding=utf-8&useSSL=false",
                this.databaseType.getName(),
                this.host,
                this.port,
                this.database);
    }
}
