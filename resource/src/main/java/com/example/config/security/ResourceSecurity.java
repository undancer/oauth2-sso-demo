package com.example.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by undancer on 2017/5/23.
 */
@Configuration
@EnableResourceServer
public class ResourceSecurity extends ResourceServerConfigurerAdapter {

    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**")
                .access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, "/api/**")
                .access("#oauth2.hasScope('write')");
    }
}
