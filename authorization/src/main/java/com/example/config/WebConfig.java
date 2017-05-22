package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by undancer on 2017/5/23.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login").setViewName("login");
        registry.addViewController("/").setViewName("index");
    }

}
