package com.bmcapps.graphdailybriefing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GraphdailybriefingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphdailybriefingApplication.class, args);
	}

}
