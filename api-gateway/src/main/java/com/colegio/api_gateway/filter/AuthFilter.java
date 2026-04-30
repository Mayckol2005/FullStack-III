package com.colegio.api_gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 1. Dejamos pasar libremente la ruta de Login
        if (path.contains("/api/auth/login")) {
            return chain.filter(exchange);
        }

        // 2. Revisamos si trae el header "Authorization"
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete(); // Bloqueamos el paso (Error 401)
        }

        // 3. Extraemos el Token
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");

        // 4. Validamos la firma matemática del Token
        try {
            // Usamos getBytes() directo, igual que en el auth-service
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
        } catch (Exception e) {
            // Si el token expiró o es inválido, lo bloqueamos
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Si es válido, lo dejamos pasar
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // Alta prioridad
    }
}