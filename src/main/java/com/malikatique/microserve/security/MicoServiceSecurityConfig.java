package com.malikatique.microserve.security;
import org.springframework.stereotype.Component;

@Component
public class MicoServiceSecurityConfig {
    public static final String[] UN_AUTH_APIS = {
            "/common",
            "/auth",
            "/auth/send-otp",
            "/auth/register",
            "/auth/login",
            "/auth/refresh-token"
    };
}
