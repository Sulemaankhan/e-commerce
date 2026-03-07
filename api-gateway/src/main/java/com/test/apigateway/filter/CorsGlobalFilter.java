package com.test.apigateway.filter;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * Adds CORS headers to every response right before commit so they are never
 * overwritten by the proxied backend. Fixes ERR_NETWORK after login when loading products.
 */
@Component
public class CorsGlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {

	private static final List<String> ALLOWED_ORIGINS = List.of("http://localhost:3000", "http://127.0.0.1:3000");

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
		exchange.getResponse().beforeCommit(() -> {
			HttpHeaders headers = exchange.getResponse().getHeaders();
			// Set (overwrite) so we always have exactly one CORS set even after proxy
			String origin = exchange.getRequest().getHeaders().getFirst(HttpHeaders.ORIGIN);
			if (origin != null && ALLOWED_ORIGINS.contains(origin)) {
				headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
			} else {
				headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALLOWED_ORIGINS.get(0));
			}
			headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS, PATCH");
			headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
			headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
			headers.set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
			return Mono.empty();
		});
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return -2;
	}
}
