package com.example.quanlyrung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("/home")
    public String getHomeView() {
        return "view-home";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @PostMapping("/login")
    public String getFailLoginView() {
        return "redirect:/login?error";
    }


}
