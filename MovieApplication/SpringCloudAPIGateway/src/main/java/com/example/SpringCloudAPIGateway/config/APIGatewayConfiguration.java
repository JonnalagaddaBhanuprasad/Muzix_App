package com.example.SpringCloudAPIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder)
    {
        return builder.routes().route(p->p
                        .path("/api/v1/**")
                        .uri("lb://user-movie-service"))  //load balancer acts as  agent of our servers and routing the client request across all server equally


                .route(p->p
                        .path("/api/v2/**")
                        .uri("lb://user-authentication-service"))

                .build();
    }
}
