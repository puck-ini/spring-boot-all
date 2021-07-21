package org.zchzh.mongo.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zchzh.mongo.constants.DocConstants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    @Resource
    private HttpServletResponse response;

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

    @Override
    public void download(String docId, HttpServletResponse response) throws Exception {
        Query query = new Query(Criteria.where(DocConstants.DocGridFsMetaData.DOC_ID).is(docId));
        GridFSFile gridFSFile = gridFsTemplate.findOne(query);
        GridFSDownloadStream gridFSDownloadStream = gridFsBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource resource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        @Cleanup OutputStream os = response.getOutputStream();
        @Cleanup InputStream is = resource.getInputStream();
        IOUtils.copy(is, os);
        os.flush();
    }
}
