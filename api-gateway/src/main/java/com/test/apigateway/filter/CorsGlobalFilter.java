package com.test.apigateway.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * Global CORS for Spring Cloud Gateway. Allows any origin (any http/https, host, port)
 * by default. Set cors.allow-all=false and cors.allowed-origins=... to restrict.
 * Handles OPTIONS preflight at the gateway so backends do not need to.
 */
@Component
public class CorsGlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {

	@Value("${cors.allow-all:true}")
	private boolean allowAllOrigins;

	@Value("${cors.allowed-origins:}")
	private String allowedOriginsConfig;

	private static final List<String> ALL_METHODS = List.of(
			"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD");

	private boolean isOriginAllowed(String origin) {
		if (origin == null || origin.isEmpty()) return false;
		if (allowAllOrigins) return true;
		if (allowedOriginsConfig != null && !allowedOriginsConfig.isBlank()) {
			List<String> allowed = Arrays.stream(allowedOriginsConfig.split(","))
					.map(String::trim)
					.collect(Collectors.toList());
			return allowed.contains("*") || allowed.contains(origin);
		}
		return true;
	}

	private void addCorsHeaders(ServerWebExchange exchange) {
		HttpHeaders headers = exchange.getResponse().getHeaders();
		String origin = exchange.getRequest().getHeaders().getFirst(HttpHeaders.ORIGIN);
		if (origin != null && isOriginAllowed(origin)) {
			headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
		}
		headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, String.join(", ", ALL_METHODS));
		headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
		headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
		headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		headers.set(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "86400");
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
		exchange.getResponse().beforeCommit(() -> {
			addCorsHeaders(exchange);
			return Mono.empty();
		});

		// Handle preflight OPTIONS at gateway so backend is not called
		if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
			exchange.getResponse().setStatusCode(HttpStatus.OK);
			return exchange.getResponse().setComplete();
		}

		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return -2;
	}
}
