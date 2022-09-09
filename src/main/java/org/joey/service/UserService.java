package org.joey.service;

import org.joey.mapper.UserMapper;
import org.joey.pojo.User;
import org.joey.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

/**
 * @author: JoeyShelby
 * @date: 2022-09-07 10:33
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名和密码查询用户是否存在
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);

        return users.size() > 0 ? users.get(0) : null;
    }

    /**
     * 添加新用户 / 注册
     * @param user
     * @return
     */
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public User queryUserByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);

        return users.size() > 0 ? users.get(0) : null;
    }
}
