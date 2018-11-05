package com.futhead.restful.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 配置静态文件路径
 * @author futhead
 *
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhost:8080/google_img/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
        registry.addResourceHandler("/google_img/**").addResourceLocations("file:D:/google_img/");
    } 

}
