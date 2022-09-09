package org.joey.controller;

import org.joey.pojo.Msg;
import org.joey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: JoeyShelby
 * @date: 2022-09-07 15:29
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // 通过用户名密码进行登录
    @PostMapping("/signin")
    @ResponseBody
    public Msg signIn(@RequestParam(value = "username")String username, @RequestParam(value = "password")String password,  HttpSession session) {
        if(userService.queryUserByUsername(username) == null){
            Msg msg = new Msg();
            msg.setCode(401);
            msg.setMsg("User does not exist");
            return msg;
        } else if(userService.queryUserByUsernameAndPassword(username, password) == null) {
            Msg msg = new Msg();
            msg.setCode(402);
            msg.setMsg("wrong password");
            return msg;
        } else {
            session.setAttribute("username", username);
            return Msg.success();
        }
    }
}
