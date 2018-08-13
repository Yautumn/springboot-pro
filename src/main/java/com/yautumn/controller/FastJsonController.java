package com.yautumn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yautumn.entity.User;
import com.yautumn.service.UserService;

@Controller
@RequestMapping("fastjson")
public class FastJsonController {
	@Autowired
    private UserService userService;
	
	@RequestMapping("/usertest")
	@ResponseBody
	public User user(int id) {
		User user = userService.getUserById(id);
		return user;
	}
}
