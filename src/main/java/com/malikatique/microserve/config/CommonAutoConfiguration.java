package com.malikatique.microserve.config;

import com.malikatique.microserve.security.ApplicationConfig;
import com.malikatique.microserve.security.JwtService;
import com.malikatique.microserve.utils.ApiResponse;
import com.malikatique.microserve.utils.ObjectsValidator;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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



//    // ExTRAAA
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService()); // set your UserDetailsService implementation here
//        provider.setPasswordEncoder(passwordEncoder()); // set your PasswordEncoder implementation here
//        return provider;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userCollection.findByEmail(username);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
