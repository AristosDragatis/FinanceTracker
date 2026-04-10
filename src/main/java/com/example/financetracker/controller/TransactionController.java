package com.example.financetracker.controller;

import com.example.financetracker.domain.Transaction;
import com.example.financetracker.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    // get all the transactions based on user ID
    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId){
        // call the service layer to bring the list of transactions
        List<Transaction> transactions = transactionService.getUserTransactions(userId);

        return ResponseEntity.ok(transactions);
    }

    // update a transaction
    @PutMapping("/{transactionId}")
    public ResponseEntity<String> updateTransaction(
            @PathVariable Long transactionId,
            @RequestParam("userId") Long userId,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("amount") BigDecimal amount,
            @RequestParam("description") String description
    ){
        // call the service layer to update the transaction
        transactionService.updateTransaction(transactionId,userId,amount,categoryId,description);

        return ResponseEntity.ok("Transaction updated successfully!");
    }
}
