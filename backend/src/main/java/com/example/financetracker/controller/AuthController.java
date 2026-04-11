package com.example.financetracker.controller;

import com.example.financetracker.domain.AppUser;
import com.example.financetracker.dto.UserLoginDTO;
import com.example.financetracker.dto.UserRegistrationDTO;
import com.example.financetracker.service.JwtService;
import com.example.financetracker.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceImpl userServiceImpl;
    private final JwtService jwtService;

    public AuthController(UserServiceImpl userServiceImpl, JwtService jwtService){
        this.userServiceImpl = userServiceImpl;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String>  registerUser(@Valid @RequestBody UserRegistrationDTO request){
        // @Valid checks if data is correct
        userServiceImpl.registerUser(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody UserLoginDTO request){

        AppUser appUser = userServiceImpl.loginUser(request.getName(), request.getPassword());
        String token = jwtService.generateToken(appUser.getName());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
