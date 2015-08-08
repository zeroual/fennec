package com.zeros.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                    .formLogin()
                    .successHandler(new RestFullAuthenticationHandler())
                    .failureHandler(new RestFullAuthenticationHandler())
                .and()
                .authorizeRequests()
                    .antMatchers("/index.html").permitAll()
                    .antMatchers("login").permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable().exceptionHandling()
                .authenticationEntryPoint(new RestFullAuthenticationHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zeros").password("zeros#password").roles("USER")
                .and()
                .withUser("jabri").password("jabri#password").roles("USER");
    }


}
