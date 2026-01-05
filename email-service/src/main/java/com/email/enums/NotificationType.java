package com.email.enums;

public enum NotificationType {

	EMAIL("email"), SMS("sms"), PUSH("push");

	private final String key;

	NotificationType(String key) {
		this.key = key;
	}

	public String key() {
		return key;
	}

}
