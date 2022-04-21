package org.zchzh.swagger.service;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.client.RestTemplate;
import org.zchzh.swagger.model.ApiMethod;
import org.zchzh.swagger.model.Parameter;
import org.zchzh.swagger.model.SwaggerV2;
import org.zchzh.swagger.model.SwaggerV3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author zengchzh
 * @date 2022/4/2
 */

@Slf4j
public class SwaggerExportFile {


    private RestTemplate restTemplate = new RestTemplate();

    private final String swaggerDocTemplate = "D:\\testdata\\swagger-my-template.docx";


    /**
     *
     * @param swaggerUrl swagger api 文档 url
     */
    public void exportSwaggerToFile(String swaggerUrl, String exportFilePath) {
        String json = restTemplate.getForObject(swaggerUrl, String.class);
        switch (checkSwaggerVersion(json)) {
            case V2:
                SwaggerV2 swaggerV2 = JSON.parseObject(json, SwaggerV2.class);
                log.info(JSON.toJSONString(swaggerV2));
                try {
                    exportSwagger2(swaggerV2, exportFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case V3:
                SwaggerV3 swaggerV3 = JSON.parseObject(json, SwaggerV3.class);
                log.info(JSON.toJSONString(swaggerV3));
                break;
            case UNKNOWN:
            default:
                throw new IllegalArgumentException();
        }
    }


    private SwaggerVersion checkSwaggerVersion(String json) {
        if (Objects.nonNull(JSON.parseObject(json).getString("swagger"))) {
            return SwaggerVersion.V2;
        } else if (Objects.nonNull(JSON.parseObject(json).getString("openapi"))) {
            return SwaggerVersion.V3;
        } else {
            return SwaggerVersion.UNKNOWN;
        }
    }


    private void exportSwagger2(SwaggerV2 swaggerV2, String exportFilePath) throws Exception {
        List<Map<String, Object>> wordData = new ArrayList<>();

        for (Map.Entry<String, Map<String, ApiMethod>> pathEntry : swaggerV2.getPaths().entrySet()) {
            Map<String, Object> formData = new HashMap<>(16);
            formData.put("url", pathEntry.getKey());
            for (Map.Entry<String, ApiMethod> methodEntry : pathEntry.getValue().entrySet()) {
                formData.put("httpMethod", methodEntry.getKey());
                ApiMethod apiMethod = methodEntry.getValue();
                formData.put("summary", apiMethod.getSummary());
                formData.put("operationId", apiMethod.getOperationId());
                formData.put("description", apiMethod.getDescription());
                List<Map<String, Object>> parameters = new ArrayList<>();
                formData.put("ps", parameters);
                for (Parameter parameter : apiMethod.getParameters()) {
                    Map<String, Object> parameterData = new HashMap<>();
                    parameterData.put("name", parameter.getName());
                    parameterData.put("in", parameter.getIn());
                    parameterData.put("required", parameter.getRequired());
                    parameterData.put("type", parameter.getType());
                    parameterData.put("description", parameter.getDescription());
                    parameters.add(parameterData);
                }
                List<Map<String, Object>> responses = new ArrayList<>();
                formData.put("resp", responses);
                for (Map.Entry<String, Object> resp : apiMethod.getResponses().entrySet()) {
//                    Map
                }
            }
            wordData.add(formData);
        }
        XWPFDocument doc = WordExportUtil.exportWord07(swaggerDocTemplate, wordData);
        File file = new File(exportFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try(FileOutputStream fos = new FileOutputStream(file)) {
            doc.write(fos);
        }
    }


    enum SwaggerVersion {
        // swagger 2
        V2,
        // swagger 3
        V3,
        // 未知的版本
        UNKNOWN
    }
}
