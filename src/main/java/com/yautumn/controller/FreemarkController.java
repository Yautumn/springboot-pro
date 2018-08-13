package com.yautumn.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("freemark")
public class FreemarkController {
	
	@RequestMapping("test")
	public String helloFreemark(Map<String,Object> map) {
		
		map.put("msg", "Hello Freemark!");
		return "test";
		
	}

}
