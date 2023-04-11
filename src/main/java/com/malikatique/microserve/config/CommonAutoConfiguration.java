package com.malikatique.microserve.config;


import com.malikatique.microserve.MicroServeApplication;
import com.malikatique.microserve.security.SecurityConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(MicroServeApplication.class)
public class CommonAutoConfiguration {
//    @Autowired
//    private SecurityConfigurations securityConfigurations;
//
//    @Bean
//    @ConditionalOnMissingBean
//    public SecurityConfigurations securityConfig() {
//        return securityConfigurations;
//    }
}
