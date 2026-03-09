package com.test.shopping.shoppingapp.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${app.jwt.secret:your-256-bit-secret-key-for-jwt-signing-must-be-long-enough}")
	private String secret;

	@Value("${app.jwt.expiration-ms:86400000}")
	private long expirationMs;

	public String generateToken(Long userId, String userName, String role) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expirationMs);
		SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8));
		return Jwts.builder()
				.subject(userName)
				.claim("userId", userId)
				.claim("role", role)
				.issuedAt(now)
				.expiration(expiry)
				.signWith(key)
				.compact();
	}
}
