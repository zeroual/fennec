package com.zeros.config;

import com.zeros.config.security.SecurityConfig;
import com.zeros.config.social.SocialConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebConfig.class,SecurityConfig.class,DomainConfig.class, SocialConfig.class})
public class ApplicationConfig {
}
