package com.yautumn.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yautumn.entity.User;
import com.yautumn.formparamter.UserForm;
import com.yautumn.service.UserService;

@Controller
@RequestMapping("freemark")
public class FreemarkController {
	
	@RequestMapping("/")
	public String freemarkerIndex() {
		return "index";
	}
	
	@Autowired
	private UserService userService;
	@RequestMapping("test/findlist")
	public ModelAndView findAllFreemark() {
		ModelAndView modelAndView = new ModelAndView("find_list");
		List<User> list = userService.getUserList();
		List<String> userList = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).userName);
			userList.add(list.get(i).getUserName());
		}
		modelAndView.addObject("list",list);
		return modelAndView;
	}
	@RequestMapping("test/findone")
	public ModelAndView findUserFreemark(@ModelAttribute("form") UserForm userForm) {
		int id = userForm.getId();
		ModelAndView modelAndView = new ModelAndView("find_one");
		User user = userService.getUserById(id);
		modelAndView.addObject("user",user);
		return modelAndView;
	}
	@RequestMapping("test/adduser")
	public ModelAndView addUserFreemark(@ModelAttribute("form")UserForm userForm) {
		String username = userForm.getUserName();
		String password = userForm.getPassword();
		Date birthday = userForm.getBirthday();
		User user = new User();
		user.setBirthday(birthday);
		user.setPassword(password);
		user.setUserName(username);
		boolean flg = userService.insertUser(user);
		ModelAndView modelAndView = new ModelAndView("add_user");
		if(flg) {
			modelAndView.addObject("stats", "插入成功");
		}else {
			modelAndView.addObject("stats", "插入失败");
			
		}
		return modelAndView;
	}
}
