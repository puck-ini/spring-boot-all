package org.zchzh.swagger.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2022/4/2
 */
@Data
public class ApiMethod {

    private List<String> tags;

    private String summary;

    private String description;

    private String operationId;

    private List<Parameter> parameters;

    private Map<String, Object> responses;


}
