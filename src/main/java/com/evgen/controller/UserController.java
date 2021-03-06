package com.evgen.controller;


import com.evgen.entity.User;
import com.evgen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService service) {
        this.userService = service;
    }

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAll());
        return "usersList";
    }

    @GetMapping("/user/{id}")
    public String getById(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "showUser";
    }

    @GetMapping("/addUser")
    public String createUserPage(){
        return "createUser";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "editUser";
    }


    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/user/" + user.getId();
    }
}


