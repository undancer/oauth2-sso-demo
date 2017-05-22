package com.undancer.ui.config;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by undancer on 2017/5/23.
 */
@Configuration
public class FilterConfig {


    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

}
