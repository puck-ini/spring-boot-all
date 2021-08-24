package org.zchzh.kkfileview.controller;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.kkfileview.service.StorageService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author zengchzh
 * @date 2021/8/20
 */

@Slf4j
@RestController
public class FileController {

    @Value("${kkfileview.path}")
    private String kkPath;

    @Autowired
    private StorageService storageService;


    @GetMapping("/preview")
    public String preview(String fileName) {
        String downloadUrl = "http://localhost:8080/download?fileName=";
        // http/https下载流url需要添加 &fullfilename= 参数指定完整的文件名， 3.x 版本以上需要 base64编码
        return kkPath + Base64Utils.encodeToString((downloadUrl + fileName + "&fullfilename=" + fileName).getBytes());
    }

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) {
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + fileName + ";filename*=utf-8''" + fileName);
            @Cleanup OutputStream os = response.getOutputStream();
            //获取数据
            @Cleanup InputStream is = storageService.getInputStream(fileName);
            IOUtils.copy(is,os);
            os.flush();
        } catch (IOException e) {
            log.error("download error", e);
        }
    }
}
