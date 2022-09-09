package org.joey.service;

import org.joey.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: JoeyShelby
 * @date: 2022-09-07 10:43
 */
@SpringBootTest
public class UserServiceTest {
    @Test
    void contextLoads() {
    }

    @Autowired
    UserService userService;

    @Test
    public void testQueryUserByUsername() {
        System.out.println(userService.queryUserByUsername("Joey").getEmail()
        );
    }

    @Test
    public void testQueryUserByUsernameAndPassword() {
        System.out.println(userService.queryUserByUsernameAndPassword("Joey", "227685").getEmail());
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("an");
        user.setPassword("1234");
        user.setName("乔义");
        user.setTel("123456");
        user.setEmail("22@qq.com");
        System.out.println(userService.addUser(user));
    }
}
