package org.zchzh.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @author zengchzh
 * @date 2021/8/13
 */

@Configuration
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enable;
    
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                // 通过url过滤一些 swagger 自带的接口
                .paths(PathSelectors.regex("(?!/.*/error.*).*"))
                .build()
                .globalRequestParameters(globalRequestParameters());
    }

    /**
     * swagger 界面信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api doc")
                .description("hello swagger")
                .version("1.0")
                .build();
    }

    /**
     * 添加全局请求参数
     * @return
     */
    private List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder builder = new RequestParameterBuilder()
                // 添加在 header 中
                .in(ParameterType.HEADER)
                .name("Authorization")
                // 是否必须
                .required(false)
                // 参数类型
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        return Collections.singletonList(builder.build());
    }
}
