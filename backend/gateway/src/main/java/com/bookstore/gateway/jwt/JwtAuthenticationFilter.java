package com.bookstore.gateway.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Collections;

public class JwtAuthenticationFilter implements WebFilter {

    @Autowired
    JWTUtilityServiceImpl jwtUtilityService;

    public JwtAuthenticationFilter(JWTUtilityServiceImpl jwtUtilityService) {
        this.jwtUtilityService = jwtUtilityService;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                JWTClaimsSet claimsSet = jwtUtilityService.parseJWT(token);
                Authentication authentication = new UsernamePasswordAuthenticationToken(claimsSet.getSubject(), null, Collections.emptyList());

                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));

            } catch (JOSEException | ParseException e) {
                // Log error y continuar sin autenticación
            } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return chain.filter(exchange);
    }
    }

