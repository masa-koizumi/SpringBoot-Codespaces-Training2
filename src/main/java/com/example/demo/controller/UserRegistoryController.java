package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserRegistoryController {

    private final UserRepository repo;

    public UserRegistoryController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public String create(String name) {
        User u = new User();
        u.setName(name);
        repo.save(u);
        return "redirect:/menu";
    }
}
