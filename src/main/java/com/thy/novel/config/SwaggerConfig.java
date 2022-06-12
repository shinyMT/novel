package com.thy.novel.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Author: thy
 * Date: 2022/4/25 14:37
 */
@Configuration
@EnableOpenApi
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
    public Docket initDocket(Environment env){
        Profiles profile = Profiles.of("dev", "prod");
        boolean flag = env.acceptsProfiles(profile);
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
}
