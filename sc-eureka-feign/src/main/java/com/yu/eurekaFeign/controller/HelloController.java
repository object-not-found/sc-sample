package com.yu.eurekaFeign.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
public class HelloController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Value("${book.name}")
	private String bookName;
	@Value("${book.auth}")
	private String bookAuth;
	@Autowired
	private DiscoveryClient dc;
	
	@RequestMapping(value = "/getBookDetail", method = RequestMethod.GET)
	public String getBookDetail(){
		ServiceInstance si = dc.getLocalServiceInstance();
		log.info("/getBookDetail, host: {}, service_id: {}", si.getHost(), si.getServiceId());
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", bookName);
		map.put("auth", bookAuth);
		return JSONObject.toJSONString(map);
	}
}
