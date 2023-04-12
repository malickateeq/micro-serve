package com.malikatique.microserve.config;

import com.malikatique.microserve.security.JwtService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
@ConditionalOnClass({JwtService.class, TestService.class})
public class CommonAutoConfiguration {

    @Autowired
    private JwtService jwtService;

    @Bean
    @ConditionalOnMissingBean
    public JwtService jwtService() {
        return this.jwtService;
    }

    @Bean
    @ConditionalOnMissingBean
    public TestService testService() {
        return new TestService();
    }

}
