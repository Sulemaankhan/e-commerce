package com.springboot.shipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProdductShipmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdductShipmentApplication.class, args);
	}

}
