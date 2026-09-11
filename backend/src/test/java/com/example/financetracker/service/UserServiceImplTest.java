package com.example.financetracker.service;

import com.example.financetracker.domain.AppUser;
import com.example.financetracker.exception.DuplicateResourceException;
import com.example.financetracker.exception.UnauthorizedException;
import com.example.financetracker.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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

    private static final String name = "TestUser";
    private static final String email = "existing@test.com";
    private static final String password = "password123";

    private AppUser user;

    @BeforeEach
    void setUp() {
        user = new AppUser();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
    }

    // Test 1 : successful registration
    @Test
    void registerUser_Success() {

        // when you get asked for this email/name, say they do not exist (false)
        when(appUserRepository.existsByEmail(email)).thenReturn(false);
        when(appUserRepository.existsByName(name)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("hashedPassword");

        // WHEN (execution)
        AppUser result = userService.registerUser(name, email, password);

        // THEN (check)
        assertNotNull(result); // We expect a user to be returned
        assertEquals("existing@test.com", result.getEmail()); // email must match
        assertEquals("hashedPassword", result.getPassword()); // Password must be hashed

        // check if save() got called once
        verify(appUserRepository, times(1)).save(any(AppUser.class));
    }

    // Test 2: Fail due to existing Email
    @Test
    void registerUser_EmailAlreadyExists_ThrowsException() {
        // Database: When you asked for this email, say that it exists (true)
        when(appUserRepository.existsByEmail(email)).thenReturn(true);

        // WHEN & THEN ( DuplicateResourceException )
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, () -> {
            userService.registerUser("AnyName", email, "AnyPassword");
        });

        // Check if fail message is correct
        assertEquals("Email: " + email + " already exists!", exception.getMessage());

        // check if save() is invoked -> (exception)
        verify(appUserRepository, never()).save(any(AppUser.class));
    }

    @Test
    void registerUser_NameAlreadyExists_ThrowsException(){

        when(appUserRepository.existsByName(name)).thenReturn(true);

        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, () -> {
            userService.registerUser(name, "AnyEmail", "AnyPassword");
        });

        // Check if fail message is correct
        assertEquals("Name: " + name + " already exists!", exception.getMessage());

        verify(appUserRepository, never()).save(any(AppUser.class));
    }

    @Test
    void loginUser_Success(){

        when(appUserRepository.findByName(name)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);

        AppUser result = userService.loginUser(name, password);

        assertNotNull(result);
        assertEquals(user.getName(), result.getName());
        assertEquals( user.getPassword(), result.getPassword());

        verify(passwordEncoder, times(1)).matches(password, user.getPassword());
    }

    @Test
    void loginUser_UserNotFound_ThrowsException(){
        when(appUserRepository.findByName(name)).thenReturn(Optional.empty());

        // WHEN THEN
        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            userService.loginUser(name, "AnyPassword");
        });

        // Check if fail message is correct
        assertEquals("Invalid username or password", exception.getMessage());

        // verify that matches method is never invoked
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void loginUser_WrongPassword_ThrowsException(){
        when(appUserRepository.findByName(name)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);

        UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
            userService.loginUser(name, password);
        });


        assertEquals("Invalid username or password", exception.getMessage());

        verify(passwordEncoder, times(1)).matches(password, user.getPassword());
    }
    
}