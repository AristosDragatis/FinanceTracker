package com.example.financetracker.repository;

import com.example.financetracker.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAppUserIdOrderByDateDesc(Long appUserId);

    List<Transaction> findByAppUserIdOrderByDateAsc(Long appUserId);

    List<Transaction> findByAppUserId(Long appUserId);
}
