package com.malikatique.microserve.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class MicoServiceSecurityConfig {
    public static final String[] UN_AUTH_APIS = {
            "/auth/send-otp",
            "/auth/register",
            "/auth/login",
            "/auth/refresh-token"
    };
}
