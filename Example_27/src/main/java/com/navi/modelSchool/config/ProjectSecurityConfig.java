package com.navi.modelSchool.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    /*protected void configure(HttpSecurity http) throws Exception {
        //Permit all the requests inside the web-application

        http.authorizeRequests()
                .anyRequest().permitAll()
                .and().formLogin()
                .and().httpBasic();
    }*/
protected void configure(HttpSecurity http) throws Exception {
        //Permit all the requests inside the web-application

        http.authorizeRequests()
                .anyRequest().denyAll()
                .and().formLogin()
                .and().httpBasic();
    }
}
