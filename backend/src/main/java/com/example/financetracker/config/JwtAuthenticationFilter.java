package com.example.financetracker.config;

import com.example.financetracker.domain.AppUser;
import com.example.financetracker.repository.AppUserRepository;
import com.example.financetracker.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    public JwtAuthenticationFilter(JwtService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Search for header "Authorization" in the request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // if there is not header or do not start with "Bearer " , move on
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // keep the clean token without "Bearer "
        jwt = authHeader.substring(7);

        // read the name of the user from the token; an expired or tampered token
        // must not blow up as a 500 - just continue unauthenticated so Spring returns 401
        try {
            username = jwtService.extractUsername(jwt);
        } catch (JwtException | IllegalArgumentException e) {
            logger.debug("Rejected JWT: " + e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // find the user from the database
            AppUser appUser = appUserRepository.findByName(username).orElse(null);

            if (appUser != null) {

                UserDetails userDetails = new User(appUser.getName(), appUser.getPassword(), new ArrayList<>());

                //  (Authentication Token) of Spring
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Identity into the SecurityContext ( login )
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}