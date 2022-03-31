package com.thy.novel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: thy
 * Date: 2022/3/17 11:24
 * 跨域请求配置类
 */
@Configuration
public class CrossConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将G盘下的novel文件作为服务器资源目录
        registry.addResourceHandler("/novel/**")
                .addResourceLocations("file:G:/novel/");
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:G:/img/");
    }
}
