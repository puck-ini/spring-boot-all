package org.zchzh.kkfileview.controller;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        return kkPath + "/onlinePreview?url=" + convertUrl("http://localhost:8080/download?fileName=" + fileName);
    }

    /**
     * 字符串加密，类似于JavaScript的encodeURIComponent方法
     * @param url
     * @return
     */
    private String convertUrl(String url){
        String result = "";
        try {
            result = URLEncoder.encode(url, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("%21", "!")
                    .replaceAll("%27", "'")
                    .replaceAll("%28", "(")
                    .replaceAll("%29", ")")
                    .replaceAll("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
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
