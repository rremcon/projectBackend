package com.example.projectbackend.repository;
import com.example.projectbackend.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
//    public Iterable<Account> findById();
    Optional<Account> findByUsername(String username);
//    public Iterable<Account> findByBirthDate(LocalDate date);
}
