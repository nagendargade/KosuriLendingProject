package com.example.kosuriTask.security.config;


import com.example.kosuriTask.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;


    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String username= jwtService.extractUsername(token);
        if (!jwtService.isTokenValid(token, userDetailsService.loadUserByUsername("your-username"))) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
            return;
        }
        // Check for token blacklisting
        if (tokenRepository.existsByToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is blacklisted");
            return;
        }
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }










//    final String jwt;
//    final String userEmail;
//    jwt = authHeader.substring(7);
//    userEmail = jwtService.extractUsername(jwt);
//        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
//        var isTokenValid = tokenRepository.findByToken(jwt)
//                .map(t -> !t.isExpired() && !t.isRevoked())
//                .orElse(false);
//        if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                    userDetails,
//                    null,
//                    userDetails.getAuthorities()
//            );
//            authToken.setDetails(
//                    new WebAuthenticationDetailsSource().buildDetails(request)
//            );
//            SecurityContextHolder.getContext().setAuthentication(authToken);
//        }
//    }
//        filterChain.doFilter(request, response);
//}
}

