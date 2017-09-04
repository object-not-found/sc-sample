package com.yu.eurekaRibbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/helloConsumer", method = RequestMethod.GET)
	public String helloConsumer(){
		
		return restTemplate.getForEntity("http://EUREKA-PROVIDER/hello?str=" + "consumer.", String.class).getBody();
	}
}
