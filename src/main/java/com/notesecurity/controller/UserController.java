package com.notesecurity.controller;

import com.notesecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/user")
@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/list")
    public ModelAndView get(){
        ModelAndView result = new ModelAndView("user/list");
        result.addObject("users", userService.listAll());
        return result;
    }
}
