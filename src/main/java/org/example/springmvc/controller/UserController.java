package org.example.springmvc.controller;

import org.example.springmvc.model.PageModel;
import org.example.springmvc.model.PageRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *  RestController 注解 用来将每个接口返回的 java对象 转换为json内容，不用该注解并且 方法上不添加@ResponseBody 将默认返回的字符串为jsp或者其他页面的地址
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public Object health(@RequestBody User user){

        return userService.add(user);
    }

    @GetMapping("/get")
    public Object get(String name){

        return userService.findByName(name);
    }

    @GetMapping("/list")
    public PageModel<User> list(PageRequest pageRequest){

        return userService.list(pageRequest);
    }

    @PostMapping("/update")
    public Object get(@RequestBody User user){

        return userService.update(user);
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody User user){
        return userService.delete(user);
    }
}
