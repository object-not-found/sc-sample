package com.yu.eurekaRibbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.yu.demo.service.DubboTestService;
import com.yu.demo.service.ProviderService;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ClassPathXmlApplicationContext context;
	
	@RequestMapping(value = "/helloConsumer", method = RequestMethod.GET)
	public String helloConsumer(){
		
		return restTemplate.getForEntity("http://EUREKA-PROVIDER/hello?str=" + "consumer.", String.class).getBody();
	}
	
	@RequestMapping(value = "/sayHi", method = RequestMethod.GET)
	public String sayHi(@RequestParam String str){
		ProviderService providerService = (ProviderService) context.getBean("providerService");
		return providerService.sayHi(str);
	}
	
	@RequestMapping(value = "getTestList", method = RequestMethod.GET)
	public String getTestList(@RequestParam Integer type){
		DubboTestService dubboTestService = (DubboTestService) context.getBean("dubboTestService");
		return JSONObject.toJSONString(dubboTestService.getTestList(type));
	}
}
