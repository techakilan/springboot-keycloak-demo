package com.westerly.frontend.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/")
    public String home(){
        return "home";

    }
    @GetMapping("/welcome")
    public String hello(Model model){
        model.addAttribute("message", "World");
        return "hello";

    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException{
        request.logout(); 
        return "redirect:/";

    }
}
