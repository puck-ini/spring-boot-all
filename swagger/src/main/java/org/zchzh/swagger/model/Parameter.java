package org.zchzh.swagger.model;

import lombok.Data;

import java.util.Map;

/**
 * @author zengchzh
 * @date 2022/4/2
 */

@Data
public class Parameter {

    private String name;

    private String in;

    private Boolean required;

    private Boolean allowReserved;

    private String style;

    private String type;

    private String description;

    private Map<String, String> schema;


}
