package org.zchzh.refreshconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zchzh.refreshconfig.config.AnoValueRefreshPostProcessor;
import org.zchzh.refreshconfig.properties.TestProp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zengchzh
 * @date 2021/8/6
 */

@RestController
public class DynamicConfigController {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    ConfigurableEnvironment environment;
    @Autowired
    TestProp testProp;

    @GetMapping(path = "dynamic/update")
    public TestProp updateEnvironment(String key, String value) {
        String name = "applicationConfig: [classpath:/application.yml]";
        MapPropertySource propertySource = (MapPropertySource) environment.getPropertySources().get(name);
        Map<String, Object> source = propertySource.getSource();
        Map<String, Object> map = new HashMap<>(source.size());
        map.putAll(source);
        map.put(key, value);
        environment.getPropertySources().replace(name, new MapPropertySource(name, map));

        applicationContext.publishEvent(new AnoValueRefreshPostProcessor.ConfigUpdateEvent(this, key));
        return testProp;
    }
}
