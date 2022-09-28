package com.navi.modelSchool.config;

import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //Url pattern and file name, don't need to put .html extension
        registry.addViewController("/courses").setViewName("courses");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/contact").setViewName("contact");
        /*registry.addViewController("/about").setViewName("about");
        registry.addViewController("/about").setViewName("about");*/


    }
}
