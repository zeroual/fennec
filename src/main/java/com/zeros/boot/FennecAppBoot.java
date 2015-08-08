package com.zeros.boot;

import com.zeros.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@Import({ApplicationConfig.class})
@SpringBootApplication
public class FennecAppBoot {

    public static void main(String[] args) {
        SpringApplication.run(FennecAppBoot.class,args);
    }
}
