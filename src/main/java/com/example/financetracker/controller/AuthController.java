package com.example.financetracker.controller;

import com.example.financetracker.dto.UserRegistrationDTO;
import com.example.financetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String>  registerUser(@Valid @RequestBody UserRegistrationDTO request){
        // @Valid checks if data is correct
        userService.registerUser(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok("User registered successfully!");
    }
}
