package com.github.szsalyi.pulzator.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().regexMatchers("/*");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/*");
    }
}
