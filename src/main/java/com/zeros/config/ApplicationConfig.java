package com.zeros.config;

import com.zeros.config.security.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({WebConfig.class,SecurityConfig.class})
public class ApplicationConfig {
}
