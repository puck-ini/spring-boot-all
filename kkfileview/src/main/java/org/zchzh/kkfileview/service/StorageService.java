package org.zchzh.kkfileview.service;

import java.io.InputStream;

/**
 * @author zengchzh
 * @date 2021/8/20
 */
public interface StorageService {

    /**
     * 上传文件
     * @param fileName 文件名
     * @param is 文件流
     */
    void upload(String fileName, InputStream is);

    /**
     * 通过文件名获取文件流
     * @param fileName 文件名
     * @return 返回文件名对应的文件流
     */
    InputStream getInputStream(String fileName);
}
