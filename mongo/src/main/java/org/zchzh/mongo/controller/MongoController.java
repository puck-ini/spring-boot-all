package org.zchzh.mongo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zchzh.mongo.service.MongoService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author zengchzh
 * @date 2021/4/7
 */

@Slf4j
@RequestMapping("/mongo")
@RestController
public class MongoController {

    @Autowired
    private MongoService mongoService;


    @PostMapping("/upload/sync")
    public void uploadSyncFile(@RequestPart("files") List<MultipartFile> multipartFiles) {
        long start = System.currentTimeMillis();
        for (MultipartFile file : multipartFiles) {
            long start1 = System.currentTimeMillis();
            mongoService.uploadFile(file);
            log.info("fiel spend time ------------- " + (System.currentTimeMillis() - start1));
        }
        log.info("end =================");
        log.info("spend time ------------- " + (System.currentTimeMillis() - start));
    }

    @PostMapping("/upload/async")
    public void uploadAsyncFile(@RequestPart("files")List<MultipartFile> multipartFiles) {
        long start = System.currentTimeMillis();
        multipartFiles.forEach(file -> CompletableFuture
                .runAsync(() -> {
                    ThreadLocal<Long> start1 = new ThreadLocal<>();
                    start1.set(System.currentTimeMillis());
//                    log.info("开启子线程：" + Thread.currentThread().getName());
                    String fileName = file.getOriginalFilename();
                    String fileNameWithoutSuffix = fileName.substring(0, fileName.lastIndexOf("."));
                    String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    mongoService.uploadFile(file);
//                    log.info("子线程" + Thread.currentThread().getName() + "已成功上传文件《" + fileName + "》");
                    log.info("子线程 " + Thread.currentThread().getName() + "spend time ------------- " + (System.currentTimeMillis() - start1.get()));
                    start1.remove();
                }));
        log.info("end =================");
        log.info("spend time ------------- " + (System.currentTimeMillis() - start));
    }



    @GetMapping("/download")
    public void download(@RequestParam("id") String id,
                         HttpServletResponse response) {
        try {
            mongoService.download(id, response);
        } catch (Exception e) {
            log.error("download error", e);
        }
    }
}
