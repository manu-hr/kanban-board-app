package com.todo.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
    
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                        .route(p -> p.path("/authentication/**").uri("http://user-auth-app:8888/*"))
                        .route(p -> p.path("/api/**").uri("http://kanban-board-app:8085/*"))
                        .build();
    }


}