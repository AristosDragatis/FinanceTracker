package com.example.financetracker.controller;

import com.example.financetracker.domain.FilterType;
import com.example.financetracker.domain.Transaction;
import com.example.financetracker.dto.TransactionDTO;
import com.example.financetracker.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> saveTransaction(@Valid @RequestBody TransactionDTO request){
        transactionService.addTransaction(request.getUserId(), request.getCategoryId(), request.getAmount(), request.getDescription());
        return ResponseEntity.ok("Transaction saved!");
    }

    // get all the transactions based on user ID
    @GetMapping("/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions(
            @PathVariable Long userId,
            @RequestParam("filterType")FilterType filterType,
            @RequestParam("filterValue") String filterValue
    ){
        // call the service layer to bring the list of transactions
        List<Transaction> transactions = transactionService.getUserTransactions(userId, filterType, filterValue);

        return ResponseEntity.ok(transactions);
    }

    // update a transaction
    @PutMapping("/{transactionId}")
    public ResponseEntity<String> updateTransaction(
            @PathVariable Long transactionId,
            @Valid @RequestBody TransactionDTO request){
        // call the service layer to update the transaction
        transactionService.updateTransaction(transactionId, request.getUserId(),request.getAmount(),request.getCategoryId(), request.getDescription());

        return ResponseEntity.ok("Transaction updated successfully!");
    }

    // delete a transaction
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId, @RequestParam("userId") Long userId){
        transactionService.deleteTransaction(transactionId, userId);

        return ResponseEntity.ok("Transaction deleted successfully!");
    }
}
