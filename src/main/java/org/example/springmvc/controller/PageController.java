package org.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("message", "Hello Thymeleaf!");
        //http://localhost:8081/page/index
        return "index";
    }
}
