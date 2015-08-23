package com.zeros.config;

import com.zeros.domain.repository.MocksRepository.MockPostRepository;
import com.zeros.domain.repository.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = DomainConfig.class)
public class DomainDevConfig {

    @Bean
    public PostRepository postRepository() {
        return new MockPostRepository();
    }
}
