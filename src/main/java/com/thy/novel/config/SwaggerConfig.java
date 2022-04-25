package com.thy.novel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: thy
 * Date: 2022/4/25 14:37
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${version}")
    private String version;

    @Value("${spring.profiles.active}")
    private String activeProp;

    @Value("${server.api-host}")
    private String apiHost;

    @Value("${spring.application.name}")
    private String serviceName;

    /**
     * 创建api基本信息
     * */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(serviceName + "API文档")
                .description(serviceName)
                .version(version)
                .build();
    }

    /**
     * 添加api相关信息
     * */
    @Bean
    public Docket createRestApi(){
        final var docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("novel")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.thy.novel.controller"))
                .paths(PathSelectors.any())
                .build();
        if("prod".equals(activeProp)){
            docket.host(apiHost);
        }

        return docket;
    }
}
