package com.example.financetracker.dto;


import com.example.financetracker.service.UserService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {

    public UserLoginDTO(){}

    @NotBlank(message = "Name must not be empty!")
    @Size(min = 4, max = 50)
    private String name;

    @NotBlank(message = "Password must not be empty!")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
