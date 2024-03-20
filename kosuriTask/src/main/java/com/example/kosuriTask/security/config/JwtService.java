package com.example.kosuriTask.security.config;



import com.example.kosuriTask.entity.CustomerRegistration;
import com.example.kosuriTask.entity.FinancierRegistration;
import com.example.kosuriTask.entity.enumValues.UserType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.*;
import java.util.function.Function;


@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userType", getUserTypeFromUserDetails(userDetails));
        return buildToken(extraClaims, userDetails, jwtExpiration, "your-issuer", "your-audience");
    }

    private UserType getUserTypeFromUserDetails(UserDetails userDetails) {
        if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_FI"))) {
            return UserType.FI;
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_SUADM"))) {
            return UserType.SUADM;
        } else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CR"))) {
            return UserType.CR;
        }
        return null;
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiration, "your-issuer", "your-audience");
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails,
                              long expiration, String issuer, String audience) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public UserDetails getUserDetails(FinancierRegistration registration) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + registration.getUserType().name()));

        return new User(
                registration.getEmail(),
                registration.getPassword(),
                authorities
        );
    }

    public UserDetails getUserDetails(CustomerRegistration registration) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + registration.getUserType().name()));

        return new User(
                registration.getEmail(),
                registration.getPassword(),
                authorities
        );
    }




}
