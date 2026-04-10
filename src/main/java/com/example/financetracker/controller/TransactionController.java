package com.example.financetracker.controller;

import com.example.financetracker.domain.Transaction;
import com.example.financetracker.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    // constructor injection
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }


    // Add a transaction
    @PostMapping("/save_transaction")
    public ResponseEntity<String> saveTransaction(
            @RequestParam("userId") Long userId,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("amount") BigDecimal amount,
            @RequestParam("description") String description){
        transactionService.addTransaction(userId, categoryId, amount, description);
        return ResponseEntity.ok("Transaction saved!");
    }


}
