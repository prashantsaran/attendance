package com.attendance.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter implements ApplicationContextAware {

    private final JwtUtil jwtUtil;
    private ApplicationContext context;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        try {
            if (header != null && header.startsWith("Bearer ")) {
                token = header.substring(7);
                username = jwtUtil.extractUsername(token); // may throw ExpiredJwtException, SignatureException etc.
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetailsService userDetailsService = context.getBean(UserDetailsService.class);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtUtil.validateToken(token)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException ex) {
            setUnauthorizedResponse(response, "JWT token expired");
        } catch (SignatureException ex) {
            setUnauthorizedResponse(response, "Invalid JWT signature");
        } catch (JwtException ex) { // any other JWT error
            setUnauthorizedResponse(response, "Invalid JWT token");
        }
    }

    private void setUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + message + "\"}");
    }
}
