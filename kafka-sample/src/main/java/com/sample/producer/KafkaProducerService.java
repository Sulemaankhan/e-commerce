package com.sample.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sample.config.ApplicationConstents;

@Service
public class KafkaProducerService {

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		logger.info(String.format("Message sent-> %s", message));
		this.kafkaTemplate.send(ApplicationConstents.Topic_name, message);
	}

}
