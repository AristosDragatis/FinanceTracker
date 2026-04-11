package com.example.financetracker.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class TransactionDTO {

    // empty constructor
    public TransactionDTO(){}

    @NotNull(message = "Choose a category.")
    private String categoryName;

    @NotNull(message = "Amount must not be empty.")
    @Positive(message = "Amount cannot be less than 0.")
    private BigDecimal amount;

    @Size(max = 255, message = "Description max characters exceeded (Total = 255)!")
    private String description;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(String categoryName) {
        this.categoryName= categoryName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
