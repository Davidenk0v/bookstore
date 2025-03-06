package com.bookstore.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book-service", r -> r.path("/api/v1/book/**")
                        .uri("http://localhost:8083"))  // URL del microservicio de libros
                .route("user-service", r -> r.path("/api/v1/user/**")
                        .uri("http://localhost:8082"))  // URL del microservicio de usuarios
                .route("borrow-service", r -> r.path("/api/v1/borrow/**")
                        .uri("http://localhost:8084"))  // URL del microservicio de prestamos
                .build();
    }
}
