//package com.malikatique.microserve.security;
//
//import com.malikatique.microserve.exception.AuthException;
//import com.malikatique.microserve.models._RefreshToken;
//import com.malikatique.microserve.models._User;
//import com.malikatique.microserve.repository._RefreshTokenRepository;
//import com.malikatique.microserve.repository._UserRepository;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
////@Service
//public class JwtService {
//
//    private static final String SECRET_KEY = "703273357538782F413F4428472B4B6250655368566D59713374367739792442";
//    private static final Long EXPIRATION_TIME = Long.valueOf(1000 * 60 * 5); // 5 Minutes
//    private static final Long REFRESH_EXPIRATION_TIME = Long.valueOf(1000 * 60 * 60 * 24 * 5);  // 5 Days
//    private final _RefreshTokenRepository refreshTokenRepository;
//    private final _UserRepository userRepository;
//
//    public JwtService(_RefreshTokenRepository refreshTokenRepository, _UserRepository userRepository) {
//        this.refreshTokenRepository = refreshTokenRepository;
//        this.userRepository = userRepository;
//    }
//
//    // Step#1
//    public Claims verifyToken(String token, Boolean isRefreshToken) {
//        Claims decodedToken = decodeToken(token);
//        isTokenExpired(decodedToken);
//        isTokenUserValid(decodedToken);
//        if(isRefreshToken) isRefreshTokenValid(token, decodedToken);
//        return decodedToken;
//    }
//
//    // Process#1 Decode the encrypted token
//    private Claims decodeToken(String token) {
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(getSignIngKey())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            throw new AuthException(e.getMessage());
//        }
//    }
//
//    // Process#2 Check if token is not expired
//    private boolean isTokenExpired(Claims token) {
//        if(token.getExpiration().before(new Date())) {
//            throw new AuthException("Token is expired!");
//        }
//        return true;
//    }
//
//    // Process#3 Check if token user status
//    private boolean isTokenUserValid(Claims token) {
//        _User user = userRepository.findById(token.getSubject()).orElseThrow();
//        if(user.getStatus() != 1) {
//            throw new AuthException("Your account is on hold!");
//        }
//        return true;
//    }
//
//    // Process#4 Check if refreshToken is not revoked
//    private boolean isRefreshTokenValid(String token, Claims decodedToken) {
//        _RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token);
//        var isRefreshToken = decodedToken.get("isRefreshToken");
//        if(isRefreshToken == null || (boolean)isRefreshToken != true) {
//            throw new AuthException("Your session has been expired. Please login again.");
//        }
//        if(refreshToken == null || refreshToken.getStatus() != 1 || false) {
//            throw new AuthException("Your session has been expired. Please login again.");
//        }
//        return true;
//    }
//
//    // Config: Set encryption algorithm
//    private Key getSignIngKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    // Generate RefreshToken
//    public _RefreshToken generateRefreshToken(_User user) {
//        _RefreshToken refreshToken = refreshTokenRepository.findExistingRefreshToken(user.getId());
//        if(refreshToken == null) refreshToken = new _RefreshToken();
//
//        refreshToken.setUser(user);
//        refreshToken.setIssuedAt(new Date(System.currentTimeMillis()));
//        refreshToken.setExpirationDate(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME));
//        refreshToken.setStatus(1);
//
//        String JwtRefreshToken = Jwts.builder()
//                .claim("isRefreshToken", true)
//                .setSubject(user.getId())
//                .setExpiration(refreshToken.getExpirationDate())
//                .setIssuedAt(refreshToken.getIssuedAt())
//                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
//                .compact();
//
//        refreshToken.setRefreshToken(JwtRefreshToken);
//        refreshTokenRepository.save(refreshToken);
//
//        return refreshToken;
//    }
//
//    // Generate AccessToken
//    public Map<String, Object> refreshToken(String refreshToken) {
//        Claims decodedToken = verifyToken(refreshToken, true);
//        Map<String, Object> accessToken = new HashMap<>();
//
//        String token = Jwts.builder()
//                .setSubject(decodedToken.getSubject())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .setIssuedAt(new Date())
//                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
//                .compact();
//        accessToken.put("token", token);
//        accessToken.put("expiredAt", new Date(System.currentTimeMillis() + EXPIRATION_TIME));
//        return accessToken;
//    }
//
//    // OLD Code -- redundant
//    public String extractUsername(String token) {
//        return getClaim(token, Claims::getSubject);
//    }
//    private Claims getClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignIngKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    public <T> T getClaim(String token, Function<Claims, T> claimResolver ) {
//        final Claims claims = getClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//}
