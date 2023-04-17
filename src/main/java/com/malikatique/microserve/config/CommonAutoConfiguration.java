package com.malikatique.microserve.config;

import com.malikatique.microserve.security.ApplicationConfig;
import com.malikatique.microserve.security.JwtService;
import com.malikatique.microserve.security.SecurityConfigurations;
import com.malikatique.microserve.utils.ApiResponse;
import com.malikatique.microserve.utils.ObjectsValidator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@NoArgsConstructor
@ConditionalOnClass({JwtService.class, ApplicationConfig.class})
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
    public ApiResponse apiResponse() {
        return new ApiResponse();
    }

    @Bean
    @ConditionalOnMissingBean
    public ObjectsValidator objectsValidator() {
        return new ObjectsValidator();
    }

}
