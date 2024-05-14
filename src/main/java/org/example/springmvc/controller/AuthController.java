package org.example.springmvc.controller;

import org.example.springmvc.model.R;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@RequestMapping
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        User data = userService.login(user);
        return data;
    }

    /**
     * 退出登录
     * 从header中获取鉴权token
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Object logout(HttpServletRequest request){
        String token = request.getHeader("token");
        userService.logout(token);
        return Collections.singletonMap("code", "200");
    }

    /**
     * 鉴权
     * @return
     */
    @GetMapping("/authDemo")
    public Object authDemo(){
        return R.ok("访问成功");
    }
}
