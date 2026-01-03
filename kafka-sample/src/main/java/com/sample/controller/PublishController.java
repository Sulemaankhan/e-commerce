package com.sample.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.producer.KafkaProducerService;

@RestController
public class PublishController {

	@Autowired
	private final KafkaProducerService kafkaProducerService;

	public PublishController(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	@PostMapping("publish")
	public String sendMessageTOKafkaProducer(@RequestParam("message") String message) {
		String s="Hi";
		this.kafkaProducerService.sendMessage(message);
		return "publish"+message;
	}

	@Autowired
	Environment environment;

	@GetMapping("port")
	public String getInfo() {
		String port = environment.getProperty("local.server.port");
		return "From server " + port;
	}
}
