package com.ashishrai.microservices.limits_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashishrai.microservices.limits_service.bean.Limits;
import com.ashishrai.microservices.limits_service.configuration.Configuration;

@RestController
public class LimitsController {
	
	//@Autowired
	private Configuration configuration;
	
	public LimitsController(Configuration configuration) {
		this.configuration = configuration;
	}



	@GetMapping("/limits")
	public Limits retrieveLimits() {
		
		return new Limits(configuration.getMinimum(), configuration.getMaximum());
	}

}
