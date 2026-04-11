package com.example.financetracker.service;

import com.example.financetracker.domain.AppUser;
import com.example.financetracker.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enable Mockito
class UserServiceImplTest {

    @Mock
    private AppUserRepository appUserRepository; // Fake database

    @Mock
    private PasswordEncoder passwordEncoder; //  Fake Encoder

    @InjectMocks
    private UserServiceImpl userService; // testing of the real service

    // Test 1 : successful registration
    @Test
    void registerUser_Success() {
        // GIVEN
        String name = "TestUser";
        String email = "test@test.com";
        String password = "password123";

        // when you get asked for this email/name, say they do not exist (false)
        when(appUserRepository.existsByEmail(email)).thenReturn(false);
        when(appUserRepository.existsByName(name)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("hashedPassword");

        // WHEN (execution)
        AppUser result = userService.registerUser(name, email, password);

        // THEN (check)
        assertNotNull(result); // We expect a user to be returned
        assertEquals("test@test.com", result.getEmail()); // email must match
        assertEquals("hashedPassword", result.getPassword()); // Password must be hashed

        // check if save() got called once
        verify(appUserRepository, times(1)).save(any(AppUser.class));
    }

    // Test 2: Fail due to existing Email
    @Test
    void registerUser_EmailAlreadyExists_ThrowsException() {
        // GIVEN
        String email = "existing@test.com";
        // Database: When you asked for this email, say that it exists (true)
        when(appUserRepository.existsByEmail(email)).thenReturn(true);

        // WHEN & THEN ( RuntimeException)
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser("AnyName", email, "AnyPassword");
        });

        // Check if fail message is correct
        assertEquals("Email: " + email + " already exists!", exception.getMessage());

        // check if save() is invoked -> (exception)
        verify(appUserRepository, never()).save(any(AppUser.class));
    }
}