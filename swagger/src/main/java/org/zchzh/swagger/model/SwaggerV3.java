package org.zchzh.swagger.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2022/4/2
 */

@Data
public class SwaggerV3 {

    private String openapi;

    private Info info;

    private List<Server> servers;

    private List<Tag> tags;

    private Map<String, Map<String, ApiMethod>> paths;
}
