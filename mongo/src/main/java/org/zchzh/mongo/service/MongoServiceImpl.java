package org.zchzh.mongo.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zchzh.mongo.constants.DocConstants;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

/**
 * @author zengchzh
 * @date 2021/4/7
 */

@Slf4j
@Service
public class MongoServiceImpl implements MongoService{

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFsBucket;

    @Override
    public void uploadFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String[] strArray = Optional.ofNullable(fileName).orElse("").split("\\.");
        String fileSuffix = strArray[strArray.length - 1];
        try {
            @Cleanup InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());
            BasicDBObject metadata = new BasicDBObject(DocConstants.DocGridFsMetaData.DOC_ID, UUID.randomUUID().toString());
            metadata.append(DocConstants.DocGridFsMetaData.SUFFIX, fileSuffix);
            metadata.append(DocConstants.DocGridFsMetaData.FILE_NAME, fileName);
            gridFsTemplate.store(inputStream,fileName,fileSuffix, metadata);
        } catch (IOException e) {
            log.error("upload error--------");
            e.printStackTrace();
        }
    }
}
