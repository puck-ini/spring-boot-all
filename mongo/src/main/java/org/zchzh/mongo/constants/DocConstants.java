package org.zchzh.mongo.constants;

/**
 * @author zengchzh
 * @date 2020/12/21
 */
public final class DocConstants {

    public static final class DocGridFsMetaData{
        // gridfs metadata 前缀
        public static final String META_DATA_PREFIX = "metadata.";
        // 主键
        public static final String DOC_ID = "docId";
        // 文件名
        public static final String FILE_NAME = "fileName";
        // 后缀名 无 '.'
        public static final String  SUFFIX = "suffix";
    }

    public static final class DocCatalogConstants{
        // 根目录父id
        public static final String ROOT_CATALOG_PID = "-1";
        // 根目录等级
        public static final Integer ROOT_CATALOG_LEVEL = 1;
    }

    /**
     * GreatWall配置
     */
    public static final class GreatWallConfig {
        // session关键字
        public static final String SESSION_KEY = "GREATWALL_SESSION";
        // nacos中的服务名称
        public static final String NACOS_SERVICE_NAME = "greatwall-union";
        // 表示资料管理子系统的名称
        public static final String DOC_SYSTEM_NAME = "资料管理";
    }
}
