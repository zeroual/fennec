package com.zeros.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public final static String HOME_PAGE="/fennec.html";
    public final static String LOGIN_PAGE="/index.html";
    public final static String LOGIN_URL="/login";
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                    .loginPage(LOGIN_PAGE)
                    .loginProcessingUrl(LOGIN_URL)
                    .defaultSuccessUrl(HOME_PAGE)
                .and()
                    .logout()
                    .logoutSuccessUrl(LOGIN_PAGE)
                .and()
                .authorizeRequests()
                    .antMatchers(LOGIN_PAGE).permitAll()
                    .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zeros").password("zeros#password").roles("USER")
                .and()
                .withUser("jabri").password("jabri#password").roles("USER");
    }


}
