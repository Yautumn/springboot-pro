package com.yautumn.aspect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("aspect")
public class CustomAspectController {
	
	@CustomLog(message = "测试", operation = "测试操作")
	@RequestMapping("/aspecttest")
	@ResponseBody
	public void testAspect() {
		
	}

}
