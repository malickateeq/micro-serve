package com.malikatique.microserve.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malikatique.microserve.exception.AuthException;
import com.malikatique.microserve.models.User;
import com.malikatique.microserve.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final UserDetailsService userDetailsService;

    private final MicoServiceSecurityConfig micoServiceSecurityConfig;

    @Autowired
    private final UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("Package: doFilterInternal");
//            if(true) {
//                throw new AuthException("Token is expired!");
//            }

            // Phase#1 Exclude Un Auth APIs
            boolean isExcluded = Arrays.stream(micoServiceSecurityConfig.UN_AUTH_APIS)
                    .anyMatch(request.getRequestURI()::equals);
            if(isExcluded) {
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("Package: Here");

            // Phase#2 Validate accessToken
            final String accessToken = request.getHeader("accessToken");
            if(accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }

            Claims claims = jwtService.verifyToken(accessToken, false);
            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(claims.getSubject());
                User authUser = userRepo.findById(claims.getSubject()).orElseThrow();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        authUser,
                        "abc credentials",
                        null //userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            // If the security checks pass, continue with the filter chain
            filterChain.doFilter(request, response);
        } catch (RuntimeException ex) {
            response.setStatus(401);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("code", "401");
            responseBody.put("message", ex.getMessage());
            responseBody.put("data", "{}");
            response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

}
