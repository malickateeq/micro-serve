package com.malikatique.microserve.config;

import com.malikatique.microserve.security.ApplicationConfig;
import com.malikatique.microserve.security.SecurityConfigurations;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "com.malikatique.microserve.repository")
@Import({ ApplicationConfig.class, SecurityConfigurations.class})
public class MicroServeLoad {
}
