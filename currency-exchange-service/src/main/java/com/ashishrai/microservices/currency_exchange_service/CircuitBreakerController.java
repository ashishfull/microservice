package com.ashishrai.microservices.currency_exchange_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

	private static Logger LOGGER = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	//@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
	//@RateLimiter(name = "default")
	@Bulkhead(name = "default")
	public String sampleApi() {
		LOGGER.info("Sample api call received");
		return "Sample API";
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity(
//				"http://localhost:8080/some-dummy-url/", String.class);
//		return forEntity.getBody();
	}
	
	public String hardcodedResponse(Exception ex) {
		LOGGER.error(ex.getMessage());
		return "fallback-response";
	}
}
