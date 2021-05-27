package org.zchzh.minio;

import io.minio.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zengchzh
 * @date 2020/12/24
 */

@Slf4j
@Component
public class MinioUtil {


    @Value(value = "${minio.host}")
    private String host;

    @Value(value = "${minio.access-key}")
    private String accessKey;

    @Value(value = "${minio.secret-key}")
    private String secretKey;

    @Value(value = "${minio.default-bucket}")
    private String defaultBucket;

    private MinioClient minioClient;

    @PostConstruct
    public void init() throws Exception{
        log.info("init minio");
        log.info("connect minio");
        // 创建连接
        minioClient = MinioClient.builder()
                .endpoint(host)
                .credentials(accessKey,secretKey)
                .build();

        // 创建默认bucket
        log.info("check default bucket");
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(defaultBucket).build())){
            log.info("create default bucket");
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(defaultBucket).build());
        }
        log.info("init end");
    }


    /**
     * 判断 bucket是否存在
     * @param bucketName
     * @return
     */
    public boolean bucketExists(String bucketName){
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建 bucket
     * @param bucketName
     */
    public void makeBucket(String bucketName){
        try {
            if(!this.bucketExists(bucketName)) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的 bucket 名
     * @return
     */
    public List<String> listBucketName(){
        try {
            return minioClient.listBuckets().stream().map(Bucket::name).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 删除 bucket
     * @param bucketName
     * @return
     */
    public boolean removeBucket(String bucketName){
        try {
            if (this.bucketExists(bucketName)){
                minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取bucket 对象存储策略
     * @param bucketName
     * @return
     */
    public String getBucketPolicy(String bucketName){
        try {
            return minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 设置bucket 存储策略
     * @param bucketName
     * @param policy
     */
    public void setBucketPolicy(String bucketName,String policy){
        try {
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(policy).build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param bucketName
     * @param fileName
     * @param contentType
     * @param inputStream
     */
    public void putObject(String bucketName, String fileName, String contentType, InputStream inputStream){
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(inputStream,inputStream.available(),-1)
                    .contentType(contentType).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传到默认bucket
     * @param fileName
     * @param contentType
     * @param inputStream
     */
    public void putObject(String fileName, String contentType, InputStream inputStream){
        this.putObject(defaultBucket,fileName,contentType,inputStream);
    }

    /**
     * 文件上传
     * @param bucketName
     * @param multipartFile
     * @throws Exception
     */
    public void putObject(String bucketName, MultipartFile multipartFile) throws Exception{
        this.putObject(bucketName,
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getInputStream());
    }

    /**
     * 文件上传到默认bucket
     * @param multipartFile
     * @throws Exception
     */
    public void putObject(MultipartFile multipartFile) throws Exception{
        this.putObject(defaultBucket, multipartFile);
    }

    /**
     * 文件上传
     * @param bucketName
     * @param fileName
     * @param contentType
     * @param filePath
     */
    public void putObject(String bucketName, String fileName, String contentType, String filePath) {
        try {
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .filename(filePath)
                    .contentType(contentType)
                    .build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 文件上传到默认bucket
     * @param fileName
     * @param contentType
     * @param filePath
     */
    public void putObject(String fileName, String contentType, String filePath) {
        this.putObject(defaultBucket,fileName,contentType,filePath);
    }


    /**
     * 删除文件
     * @param bucketName
     * @param objectName
     */
    public void removeObject(String bucketName, String objectName){
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除默认bucket 文件
     * @param objectName
     */
    public void removeObject(String objectName){
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(defaultBucket).object(objectName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件input stream
     * @param bucketName
     * @param objectName
     * @return
     */
    public InputStream getObject(String bucketName, String objectName){
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从默认bucket 中获取文件 input stream
     * @param objectName
     * @return
     */
    public InputStream getObject(String objectName){
        return this.getObject(defaultBucket,objectName);
    }

    /**
     * 下载文件
     * @param bucketName
     * @param objectName minio 中的文件名
     * @param filePath 下载到的文件路径 例如C:/user/test/test.txt
     */
    public void downloadObject(String bucketName, String objectName,String filePath){
        try {
            minioClient.downloadObject(DownloadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .filename(filePath).build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     * @param objectName minio 中的文件名
     * @param filePath 下载到的文件路径 例如C:/user/test/test.txt
     */
    public void downloadObject(String objectName,String filePath){
        this.downloadObject(defaultBucket, objectName,filePath);
    }

    public List<String> listObject(String bucketName){
        return null;
    }

}
