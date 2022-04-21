package org.zchzh.swagger.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2022/4/2
 */

@Data
public class SwaggerV2 {

    private String swagger;

    private Info info;

    private String host;

    private List<Tag> tags;

    private Map<String, Map<String, ApiMethod>> paths;


}
