package com.zeros.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public final static String HOME_PAGE="/fennec";
    public final static String INDEX_PAGE ="/";
    public final static String LOGIN_URL="/login";
    public final static String SOCIAL_LOGIN_URL="/connect/**";
    public final static String SOCIAL_SIGNING_URL ="/signin/**";
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                    .loginPage(INDEX_PAGE)
                    .loginProcessingUrl(LOGIN_URL)
                    .defaultSuccessUrl(HOME_PAGE)
                .and()
                    .logout()
                    .logoutSuccessUrl(INDEX_PAGE)
                .and()
                .authorizeRequests()
                    .antMatchers(INDEX_PAGE).permitAll()
                    .antMatchers(SOCIAL_SIGNING_URL).permitAll()
                    .antMatchers(SOCIAL_LOGIN_URL).permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/js/**", "/css/**", "/images/**", "/**/*.jpg");
    }


}
