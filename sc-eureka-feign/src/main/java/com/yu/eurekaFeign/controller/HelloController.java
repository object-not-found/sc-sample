package com.yu.eurekaFeign.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;


@RestController
public class HelloController {

	@Value("${book.name}")
	private String bookName;
	@Value("${book.auth}")
	private String bookAuth;
	
	@RequestMapping(value = "/getBookDetail", method = RequestMethod.GET)
	public String getBookDetail(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", bookName);
		map.put("auth", bookAuth);
		return JSONObject.toJSONString(map);
	}
}
