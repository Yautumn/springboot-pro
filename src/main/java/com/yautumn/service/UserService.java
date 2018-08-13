package com.yautumn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.yautumn.common.UserRowMapper;
import com.yautumn.entity.User;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUserList(){
        String sql = "SELECT * FROM USER";
        RowMapper<User> rowMapper = new UserRowMapper();
        return jdbcTemplate.query(sql,rowMapper);
    }
    public User getUserById(int id){
        String sql = "select * from user where id = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }
    public int getCount(){
        String sql = "select count(1) from user";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
    public boolean deleteUserById(int id){
        boolean flg = false;
        String sql = "delete from user where id = ?";
        int i = jdbcTemplate.update(sql,id);
        if(i>0){
            flg = true;
        }
        return flg;
    }
    public boolean insertUser(User user){
        boolean flg = false;
        String sql = "insert into user values(?,?,?,?)";
        int i = jdbcTemplate.update(sql,null,user.getUserName(),user.getPassword(),user.getBirthday());
        if(i>0){
            flg = true;
        }
        return flg;
    }
}
