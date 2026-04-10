package com.example.financetracker.controller;

import com.example.financetracker.service.UserService;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String>  registerUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password){
        userService.registerUser(name, email, password);
        return ResponseEntity.ok("User registered successfully!");
    }


}
