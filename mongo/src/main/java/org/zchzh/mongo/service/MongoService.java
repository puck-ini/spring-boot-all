package org.zchzh.mongo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zengchzh
 * @date 2021/4/7
 */
public interface MongoService {


    /**
     * 上传文件
     * @param multipartFile 上传的文件
     */
    void uploadFile(MultipartFile multipartFile);

    void download(String docId, HttpServletResponse response) throws Exception;
}
