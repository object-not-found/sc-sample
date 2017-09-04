package com.yu.eurekaProvider.controller;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/hello", method  = RequestMethod.GET)
	public String  hello(@NotNull(message = "the parameter 'str' is null or empty.") @RequestParam("str") String str){
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("host: {}, serviceId: {}", instance.getHost(), instance.getServiceId());
		return "hello, " + str;
	}
}
