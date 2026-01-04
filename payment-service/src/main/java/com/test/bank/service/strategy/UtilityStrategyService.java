package com.test.bank.service.strategy;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.bank.service.PaymentStrategy;

@Service
public class UtilityStrategyService {
	
	private final Map<String, PaymentStrategy> strategies;

	public UtilityStrategyService(Map<String, PaymentStrategy> strategies) {
		this.strategies = strategies;
	}

	public PaymentStrategy getPaymentMode(String mode) {
		PaymentStrategy strategy = strategies.get(mode);
		if (strategy == null) {
			throw new IllegalArgumentException("Unknown payment type: " + mode);
		}
		return strategy;
	}
}
