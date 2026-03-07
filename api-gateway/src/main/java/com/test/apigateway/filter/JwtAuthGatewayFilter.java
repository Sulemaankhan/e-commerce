package com.test.apigateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthGatewayFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {

	private static final String BEARER_PREFIX = "Bearer ";
	private static final List<String> PUBLIC_PATH_PREFIXES = List.of(
			"/shopping-service/users/login",
			"/shopping-service/users/register",
			"/actuator/",
			"/shopping-service/products",
			"/eureka/"
	);

	@Value("${app.jwt.secret:your-256-bit-secret-key-for-jwt-signing-must-be-long-enough}")
	private String secret;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
		// Handle CORS preflight at gateway; do not forward OPTIONS to backend (backend has no OPTIONS handler)
		if ("OPTIONS".equalsIgnoreCase(exchange.getRequest().getMethod().name())) {
			return handleCorsPreflight(exchange);
		}
		String path = exchange.getRequest().getPath().value();
		if (PUBLIC_PATH_PREFIXES.stream().anyMatch(p -> path.equals(p) || path.startsWith(p))) {
			return chain.filter(exchange);
		}
		String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		if (auth == null || !auth.startsWith(BEARER_PREFIX)) {
			return unauthorized(exchange, "Missing or invalid Authorization header");
		}
		String token = auth.substring(BEARER_PREFIX.length()).trim();
		try {
			byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
			Claims claims = Jwts.parser()
					.verifyWith(Keys.hmacShaKeyFor(keyBytes))
					.build()
					.parseSignedClaims(token)
					.getPayload();
			String username = claims.getSubject();
			Object userId = claims.get("userId");
			ServerWebExchange modified = exchange.mutate()
					.request(r -> r.headers(h -> {
						if (username != null) h.set("X-Username", username);
						if (userId != null) h.set("X-User-Id", String.valueOf(userId));
					}))
					.build();
			return chain.filter(modified);
		} catch (Exception e) {
			return unauthorized(exchange, "Invalid or expired token");
		}
	}

	private Mono<Void> handleCorsPreflight(ServerWebExchange exchange) {
		exchange.getResponse().setStatusCode(HttpStatus.OK);
		return exchange.getResponse().setComplete();
	}

	private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		byte[] body = ("{\"message\":\"" + message.replace("\"", "\\\"") + "\"}").getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(body);
		return exchange.getResponse().writeWith(Mono.just(buffer));
	}

	@Override
	public int getOrder() {
		return -1;
	}
}
