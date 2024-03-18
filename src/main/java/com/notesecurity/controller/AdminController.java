package com.notesecurity.controller;
import com.notesecurity.database.entity.Role;
import com.notesecurity.database.entity.User;
import com.notesecurity.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/list")
    public ModelAndView get(){
        ModelAndView result = new ModelAndView("admin/list");
        result.addObject("users", userService.listAll());
        return result;
    }
    @PostMapping("/delete")
    public String deleteNoteById(
            @Valid
            @NotNull @RequestParam("id") UUID id) {
        userService.deleteById(id);
        return "redirect:/admin/list";
    }
    @GetMapping("/edit")
    public ModelAndView editUserById(
            @Valid
            @NotNull @RequestParam("id") UUID id) throws Exception {
        ModelAndView result = new ModelAndView("admin/edit");
        result.addObject("user", userService.getById(id));
        return result;
    }
    @PostMapping("/edit")
    public ModelAndView updateUser(
            @Valid
            @NotNull @RequestParam(value="id") UUID id,
            @NotNull @RequestParam(value="userName") String userName,
            @NotNull @RequestParam(value="password") String password,
            @NotNull @RequestParam(value="role") String role) {
        User user = User.builder()
                .id(id)
                .userName(userName)
                .password(password)
                .role(Role.valueOf(role))
                .build();
        userService.update(user);
        return get();
    }
}
