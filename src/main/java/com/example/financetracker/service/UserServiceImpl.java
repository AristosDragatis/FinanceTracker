package com.example.financetracker.service;

import com.example.financetracker.domain.AppUser;
import com.example.financetracker.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder){
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser registerUser(String name, String email, String rawPassword){
        // check if email already exists
        if(appUserRepository.existsByEmail(email)){
            throw new RuntimeException("Email: " + email + " already exists!");
        }

        // check if name already exists
        if(appUserRepository.existsByName(name)){
            throw new RuntimeException("Name: " + name + " already exists!");
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);

        AppUser newUser= new AppUser();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPassword(encodedPassword);

        appUserRepository.save(newUser);
        return newUser;
    }


    @Override
    public AppUser loginUser(String name, String rawPassword){
        // find user by name
        AppUser user = appUserRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Wrong username or password"));

        if(!passwordEncoder.matches(rawPassword, user.getPassword())){
            throw new RuntimeException("Incorrect password!");
        }
        return user;
    }
}
