package com.malikatique.microserve.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfigurations {

//    private final JwtAuthFilter jwtAuthFilter;

    private MicoServiceSecurityConfig micoServiceSecurityConfig;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Package: filterChain");
        http
            .csrf().disable() // Disable CSRF
            .authorizeHttpRequests() // Start authorizing HTTP requests
            .requestMatchers(micoServiceSecurityConfig.UN_AUTH_APIS) // Exclude these API patterns
            .permitAll() // Add above patterns to whitelist
            .anyRequest() // Select all other requests except the above ones
            .authenticated() // And make them authenticated
            .and()
            .sessionManagement() // Select Session Mgmt
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Disable Spring to create sessions
            .and()
            .authenticationProvider(authenticationProvider);
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
