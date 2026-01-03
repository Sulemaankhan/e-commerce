package com.sample.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import com.sample.config.ApplicationConstents;

@Service
public class KafkaConsumerService {

	private final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

	@KafkaListener(topics = ApplicationConstents.Topic_name, groupId = ApplicationConstents.Group_id)
	public void consume(String message) {
		logger.info("message recived -> %s", message);
	}

}
