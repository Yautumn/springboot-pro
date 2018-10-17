package com.yautumn.controller;

import com.google.gson.Gson;
import com.yautumn.entity.User;
import com.yautumn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getlist")
    public String getUserList(){
        List<User> list = userService.getUserList();
        return new Gson().toJson(list);
    }
    @RequestMapping("/getuserbyid")
    public String getUserById(int id){
        User user = userService.getUserById(id);
        return new Gson().toJson(user);
    }
    @RequestMapping("/getcount")
    public int getUserCount(){
        int count = userService.getCount();
        return count;
    }
    @RequestMapping("/deletebyid")
    public String deleteUserById(int id){
        boolean flg = userService.deleteUserById(id);
        if(flg == true){
            return "删除成功";
        }
        return "删除失败";
    }
    @RequestMapping("/insertuser")
    public String insertUser(){
        User user = new User();
        user.setUserName("kjlkd");
        user.setPassword("1234");
        boolean flg = userService.insertUser(user);
        if (flg == true){
            return "插入成功";
        }
        return "插入失败";
    }
    
    @RequestMapping("/updateuser")
    public String updateUser(){
        User user = userService.getUserById(5);
        user.setUserName("user1");
        user.setPassword("user1");
        boolean flg = userService.updateUser(user);
        if (flg == true){
            return "更新成功";
        }
        return "更新失败";
    }
}
