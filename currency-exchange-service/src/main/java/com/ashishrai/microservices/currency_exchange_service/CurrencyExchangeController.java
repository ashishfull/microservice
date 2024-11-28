package com.ashishrai.microservices.currency_exchange_service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private CurrencyExchangeRepository repository;
	private Environment environment;
	
	public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
		this.environment = environment;
		this.repository = repository;
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		Optional<CurrencyExchange> currencyExchange = repository.findByFromAndTo(from, to);
		if(currencyExchange.isEmpty()) {
			throw new RuntimeException("No data for " + from + " & " + to);
		}
		String port = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchangeObject = currencyExchange.get();
		currencyExchangeObject.setEnvironment(port);
		return currencyExchangeObject;
	}

}
