package com.example.financetracker.repository;

import com.example.financetracker.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserMapper extends JpaRepository<AppUser, Long> {
    // Long is the type of the user ID primary key

    // find user based on email
    Optional<AppUser> findByEmail(String email);

    // check if the email already exists.
    boolean existsByEmail(String email);

}
