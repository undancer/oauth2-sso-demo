package com.undancer.id.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by undancer on 2017/5/23.
 */
@Configuration
//@Order(-20)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers()
                .antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
