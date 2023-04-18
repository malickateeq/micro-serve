package com.malikatique.microserve.config;

import com.malikatique.microserve.security.*;
import com.malikatique.microserve.utils.ObjectsValidator;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.malikatique.microserve.repository")
@Import({ 
        ApplicationConfig.class, 
        SecurityConfigurations.class, 
        JwtAuthFilter.class, 
        JwtService.class, 
        MicoServiceSecurityConfig.class,
        ObjectsValidator.class
})
public class MicroServeLoad {
}
