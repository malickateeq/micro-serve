package com.malikatique.microserve.config;

import com.malikatique.microserve.security.JwtService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(JwtService.class)
public class CommonAutoConfiguration {

    private final JwtService jwtService;

    public CommonAutoConfiguration(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtService jwtService() {
        return this.jwtService;
    }

}
