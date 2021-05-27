package org.zchzh.minio;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author zengchzh
 * @date 2020/12/24
 */

@RequestMapping("/minio")
@RestController
@Slf4j
public class TestController {


    @Autowired
    private MinioUtil minioUtil;

    @PostMapping("/upload")
    public void uploadFile(@RequestPart("file") MultipartFile file) throws Exception {
        minioUtil.putObject(file);
    }


    @GetMapping("/download/stream")
    public void downloadFileStream(@RequestParam("fileName") String fileName,
                             HttpServletResponse response) throws Exception{

        fileName = URLEncoder.encode(fileName,"UTF-8");
        response.setHeader("Access-Control-Expose-Headers","Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName);
        response.setContentType("application/octet-stream");
        OutputStream os = response.getOutputStream();
        //获取数据
        InputStream inputStream = minioUtil.getObject(fileName);
        IOUtils.copy(inputStream,os);
        os.flush();
        os.close();
    }

    @GetMapping("/download/path")
    public void downloadFilePath(@RequestParam("fileName") String fileName,
                             @RequestParam("filePath") String filePath) throws Exception{
        minioUtil.downloadObject(fileName,filePath);
    }

    public static void main(String[] args) {
        String str = "*adCVs*34_-----a _09_b5*[/435^*&城池()^$'$&*).{'}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";
//        str = "(*)*()**@!";
        System.out.println(str);
        System.out.println(stringFilter(str));
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
//        String regEx= "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}‘；：”“’。， 、？【】_]";;
        String regEx= "[`~!@#$%^&()+=|{}':;',\\[\\].<>/?~！@#￥%……&（）——+|{}【】‘；：”“’。，、？_-]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
