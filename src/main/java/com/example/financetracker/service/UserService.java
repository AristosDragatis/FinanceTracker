package com.example.financetracker.service;

import com.example.financetracker.domain.AppUser;

public interface UserService {
    AppUser registerUser(String name, String email, String rawPassword);
    AppUser loginUser(String name, String rawPassword);
}
