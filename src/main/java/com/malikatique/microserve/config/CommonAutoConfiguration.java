//package com.malikatique.microserve.config;
//
//import com.malikatique.microserve.security.JwtService;
//import com.malikatique.microserve.utils.ApiResponse;
//import com.malikatique.microserve.utils.ObjectsValidator;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@NoArgsConstructor
//// @ComponentScan("com.malikatique.microserve.models") // will pick up any classes annotated with @Component, @Service, @Repository, etc.
//@ConditionalOnClass({JwtService.class, TestService.class})
//public class CommonAutoConfiguration {
//
//    @Autowired
//    private JwtService jwtService;
//
//    @Bean
//    @ConditionalOnMissingBean
//    public JwtService jwtService() {
//        return this.jwtService;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public TestService testService() {
//        return new TestService();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public ApiResponse apiResponse() {
//        return new ApiResponse();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public ObjectsValidator objectsValidator() {
//        return new ObjectsValidator();
//    }
//
//}
