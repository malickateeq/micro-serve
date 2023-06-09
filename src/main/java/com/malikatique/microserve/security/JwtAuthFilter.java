package com.malikatique.microserve.security;

import com.malikatique.microserve.exception.AuthException;
import com.malikatique.microserve.models._User;
import com.malikatique.microserve.repository._UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
    private final _UserRepository userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Phase#1 Exclude Un Auth APIs
            String uri = request.getRequestURI();
            boolean isExcluded = Arrays.stream(micoServiceSecurityConfig.UN_AUTH_APIS)
                    .anyMatch(uri::equals);

            if(isExcluded) {
                System.out.println("Bypassing UN_AUTH_API");
                filterChain.doFilter(request, response);
                return;
            }

            System.out.println("Checking AUTH_API");
            // Phase#2 Validate accessToken
            final String accessToken = request.getHeader("accessToken");
            if(accessToken == null) {
                throw new AuthException("Invalid accessToken!");
            }

            Claims claims = jwtService.verifyToken(accessToken, false);
            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(claims.getSubject());
                _User authUser = userRepo.findById(claims.getSubject()).orElseThrow();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        authUser,
                        "abc credentials",
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            // If the security checks pass, continue with the filter chain
            System.out.println("Package: before doFilter");
            filterChain.doFilter(request, response);
            return;
        } catch (RuntimeException ex) {

            System.out.println("Package: In the Exception");
            System.out.println( ex.getMessage() );

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